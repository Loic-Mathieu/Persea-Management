package be.hers.info.persea.service.document;

import be.hers.info.persea.dao.courtcase.CourtCaseDao;
import be.hers.info.persea.dao.tag.TagDao;
import be.hers.info.persea.dao.user.UserDao;
import be.hers.info.persea.interpreter.CourtCasePropertyInterpreter;
import be.hers.info.persea.interpreter.UserPropertyInterpreter;
import be.hers.info.persea.interpreter.UtilPropertyInterpreter;
import be.hers.info.persea.util.documents.text.PerseaFileReader;
import be.hers.info.persea.util.documents.text.word.WordFileReader;
import be.hers.info.persea.exceptions.InvalidTagException;
import be.hers.info.persea.exceptions.TagCreationException;
import be.hers.info.persea.model.user.User;
import be.hers.info.persea.model.courtCase.CourtCase;
import be.hers.info.persea.model.document.Tag;
import be.hers.info.persea.util.time.PerseaTime;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.NoResultException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component(value = "serviceDocument")
public class DocumentServiceImpl implements DocumentService {

    private TagDao tagDao;
    private CourtCaseDao courtCaseDao;
    private UserDao userDao;

    @Autowired
    public DocumentServiceImpl(TagDao tagDao, CourtCaseDao courtCaseDao, UserDao userDao) {
        assert tagDao != null;
        assert courtCaseDao != null;
        assert userDao != null;

        this.tagDao = tagDao;
        this.courtCaseDao = courtCaseDao;
        this.userDao = userDao;
    }

    private File createTemporaryFile(MultipartFile multipartFile) throws IOException {
        File tempFile = File.createTempFile("TEMPORARY_DOCUMENT_DRAFT", WordFileReader.DOCUMENT_DEFAULT_SUFFIX);
        FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();
        return tempFile;
    }

    private String createPdf(CourtCase courtCase, String content) throws FileNotFoundException, DocumentException {
        Document document = new Document();
        String path = "folders/" + courtCase.getCaseNumber() + "/documents";
        String fileName = courtCase.getCaseNumber() + "__" + PerseaTime.getShortFormattedDate() + ".pdf";

        // check path
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        PdfWriter.getInstance(document, new FileOutputStream(path + "/" + fileName));

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk(content, font);

        document.add(chunk);
        document.close();

        return fileName;
    }

    /*  === PUBLIC FUNCTIONS ===    */

    @Override
    public String createDocument(MultipartFile file, long caseId) throws TagCreationException {
        try {
            PerseaFileReader fileReader = new WordFileReader(this.createTemporaryFile(file));
            String rawText = fileReader.readFile();

            CourtCase courtCase = this.courtCaseDao.getById(caseId);
            CourtCasePropertyInterpreter courtCasePropertyInterpreter = new CourtCasePropertyInterpreter(courtCase);

            // TODO get connected user
            // Change unit test
            User user = this.userDao.getById(1L);
            UserPropertyInterpreter userPropertyInterpreter = new UserPropertyInterpreter(user);

            UtilPropertyInterpreter utilPropertyInterpreter = new UtilPropertyInterpreter();

            // TODO get REGEX from db
            final Pattern TAG_PATTERN = Pattern.compile("<<(.+?)>>", Pattern.DOTALL);
            Matcher matcher = TAG_PATTERN.matcher(rawText);

            Map<String, String> knownTags = new HashMap<>();
            StringBuffer buffer = new StringBuffer();
            while (matcher.find()) {
                String rawTag = matcher.group(1);

                String translatedTag;
                if (!knownTags.containsKey(rawTag)) {
                    Tag tag = this.tagDao.getByName(rawTag);

                    switch (tag.getProperty().getPropertyType()) {
                        case CASE:
                            translatedTag = courtCasePropertyInterpreter.interpret(tag.getProperty());
                            break;
                        case STATIC:
                            translatedTag = userPropertyInterpreter.interpret(tag.getProperty());
                            break;
                        case DYNAMIC:
                            translatedTag = utilPropertyInterpreter.interpret(tag.getProperty());
                            break;
                        default:
                            throw new InvalidTagException(tag);
                    }

                    knownTags.put(rawTag, translatedTag);
                } else {
                    translatedTag = knownTags.get(rawTag);
                }
                matcher.appendReplacement(buffer, translatedTag);
            }

            if (buffer.length() <= 0) {
                throw new TagCreationException("No tag found in the document");
            }

            return this.createPdf(courtCase, buffer.toString());
        } catch (IOException e) {
            throw new TagCreationException("The file cannot be opened");
        } catch (InvalidFormatException e) {
            throw new TagCreationException("Bad Format");
        } catch (NoResultException e) {
            throw new TagCreationException("Missing target data");
        } catch (InvalidTagException e) {
            throw new TagCreationException("Bad Tag: \n" + e.getMessage());
        } catch (DocumentException e) {
            throw new TagCreationException("Can not create pdf" + e.getMessage());
        }
    }

    @Override
    public Path getDocument(long caseId, String fileName) {
        CourtCase courtCase = this.courtCaseDao.getById(caseId);
        String path = "folders/" + courtCase.getCaseNumber() + "/documents";
        return Paths.get(path, fileName);
    }

    @Override
    public Resource getFile(long caseId, String fileName) {
        CourtCase courtCase = this.courtCaseDao.getById(caseId);
        String path = "folders/" + courtCase.getCaseNumber();
        return new ClassPathResource(path + "/ filename");
    }
}
