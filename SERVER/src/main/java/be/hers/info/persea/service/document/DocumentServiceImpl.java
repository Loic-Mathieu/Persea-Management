package be.hers.info.persea.service.document;

import be.hers.info.persea.dao.courtcase.CourtCaseDao;
import be.hers.info.persea.dao.tag.TagDao;
import be.hers.info.persea.dao.user.UserDao;
import be.hers.info.persea.interpreter.CourtCasePropertyInterpreter;
import be.hers.info.persea.interpreter.UserPropertyInterpreter;
import be.hers.info.persea.interpreter.UtilPropertyInterpreter;
import be.hers.info.persea.model.bill.Bill;
import be.hers.info.persea.model.time.TimePeriod;
import be.hers.info.persea.util.documents.sheet.PerseaSheetBuilder;
import be.hers.info.persea.util.documents.sheet.xls.XlsBuilder;
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
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.springframework.util.StreamUtils.BUFFER_SIZE;

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

    private String createPdf(CourtCase courtCase, String content, String fileName)
                    throws FileNotFoundException, DocumentException {
        Document document = new Document();
        String path = "folders/" + courtCase.getCaseNumber() + "/documents";
        String finalFileName = fileName
                                + "__" + courtCase.getCaseNumber()
                                + "__" + PerseaTime.getShortFormattedDate() + ".pdf";

        // check path
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        PdfWriter.getInstance(document, new FileOutputStream(path + "/" + finalFileName));

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk(content, font);

        document.add(chunk);
        document.close();

        return finalFileName;
    }

    /*  === PUBLIC FUNCTIONS ===    */

    @Override
    public String createDocument(MultipartFile file, String fileName, long caseId) throws TagCreationException {
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

            return this.createPdf(courtCase, buffer.toString(), fileName);
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
    public String uploadFile(MultipartFile multipartFile, String fileName, long caseId) throws IOException {
        CourtCase courtCase = this.courtCaseDao.getById(caseId);

        String path = "folders/" + courtCase.getCaseNumber();
        String finalFileName = PerseaTime.getShortFormattedDate() + "__" + fileName;

        // check path
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        FileOutputStream fileOutputStream = new FileOutputStream(path + "/" + finalFileName);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();

        return finalFileName;
    }

    @Override
    public String createBill(Bill bill, long caseId) throws IOException {
        CourtCase courtCase = this.courtCaseDao.getById(caseId);
        PerseaSheetBuilder sheetBuilder = new XlsBuilder();

        // header
        sheetBuilder.append(0, 0, "Subject").append(1, 0, "cost");

        // body
        int index = 1;
        for (TimePeriod timePeriod : bill.getTimePeriods()){
            long hoursNumber = timePeriod.getDuration().toHours();
            sheetBuilder.append(0, index, timePeriod.getDescription())
                        .append(1, index, hoursNumber + "H")
                        .append(2, index, (hoursNumber * bill.getBasePrice()) + "€");
            if (timePeriod.getSupplement() > 0) {
                sheetBuilder.append(3, index, "+" + timePeriod.getSupplement() + "€");
            }

            index++;
        }

        // total
        sheetBuilder.append(0, index + 2, "Total :")
                    .append(1, index + 2, bill.getTotalHours() + "h")
                    .append(2, index + 2, bill.getTotalPrice() + "€")
                    .append(3, index + 2, (bill.getRate() * 100) + "%")
                    .changeTitle(bill.getBillNumber());

        String path = "folders/" + courtCase.getCaseNumber() + "/bills";
        String fileName = bill.getBillNumber() + ".xls";

        // check path
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        sheetBuilder.build(path + "/" + fileName);

        return fileName;
    }

    private void zipDirectory(File folder, String parentFolder,
                              ZipOutputStream zipOutputStream) throws FileNotFoundException, IOException {
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                zipDirectory(file, parentFolder + "/" + file.getName(), zipOutputStream);
                continue;
            }
            zipOutputStream.putNextEntry(new ZipEntry(parentFolder + "/" + file.getName()));
            BufferedInputStream bis = new BufferedInputStream(
                    new FileInputStream(file));
            long bytesRead = 0;
            byte[] bytesIn = new byte[BUFFER_SIZE];
            int read = 0;
            while ((read = bis.read(bytesIn)) != -1) {
                zipOutputStream.write(bytesIn, 0, read);
                bytesRead += read;
            }
            zipOutputStream.closeEntry();
        }
    }

    private void zipFile(File file, ZipOutputStream zipOutputStream) throws FileNotFoundException, IOException {
        zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
                file));
        long bytesRead = 0;
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = bis.read(bytesIn)) != -1) {
            zipOutputStream.write(bytesIn, 0, read);
            bytesRead += read;
        }
        zipOutputStream.closeEntry();
    }

    @Override
    public Path getZip(long caseId) throws IOException {
        CourtCase courtCase = this.courtCaseDao.getById(caseId);

        String path = "folders/" + courtCase.getCaseNumber();

        // check path
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File zip = File.createTempFile("TEMPORARY_ZIP_FILE", ".zip");
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zip));
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                zipDirectory(file, file.getName(), zos);
            } else {
                zipFile(file, zos);
            }
        }
        zos.flush();
        zos.close();

        return Paths.get(zip.getPath());
    }

    @Override
    public Path getDocument(long caseId, String fileName) {
        CourtCase courtCase = this.courtCaseDao.getById(caseId);
        String path = "folders/" + courtCase.getCaseNumber() + "/documents";
        return Paths.get(path, fileName);
    }

    @Override
    public Path getBill(long caseId, String fileName) {
        CourtCase courtCase = this.courtCaseDao.getById(caseId);
        String path = "folders/" + courtCase.getCaseNumber() + "/bills";
        return Paths.get(path, fileName);
    }
}
