package be.hers.info.persea.service.document;

import be.hers.info.persea.dao.courtcase.CourtCaseDao;
import be.hers.info.persea.dao.tag.TagDao;
import be.hers.info.persea.dao.user.UserDao;
import be.hers.info.persea.documents.PerseaFileReader;
import be.hers.info.persea.documents.word.WordFileReader;
import be.hers.info.persea.exceptions.InvalidTagException;
import be.hers.info.persea.exceptions.TagCreationException;
import be.hers.info.persea.model.User;
import be.hers.info.persea.model.courtCase.CourtCase;
import be.hers.info.persea.model.document.PerseaProperty;
import be.hers.info.persea.model.document.Tag;
import be.hers.info.persea.util.time.PerseaDate;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.NoResultException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

    private String translateCaseTag(Tag tag, CourtCase model) throws InvalidTagException {
        switch (tag.getProperty()) {
            case CASE_NUMBER:
                return model.getCaseNumber();
            default:
                throw new InvalidTagException(tag, PerseaProperty.PropertyType.CASE);
        }
    }

    private String translateStaticTag(Tag tag, User model) throws InvalidTagException {
        switch (tag.getProperty()) {
            case USER_NAME:
                return model.getLastName();
            default:
                throw new InvalidTagException(tag, PerseaProperty.PropertyType.STATIC);
        }
    }

    private String translateDynamicTag(Tag tag) throws InvalidTagException {
        switch (tag.getProperty()) {
            case CURRENT_DATE:
                return PerseaDate.getStandardFormattedDate();
            default:
                throw new InvalidTagException(tag, PerseaProperty.PropertyType.DYNAMIC);
        }
    }

    /*  === PUBLIC FUNCTIONS ===    */

    @Override
    public String createDocument(MultipartFile file, long caseId) throws TagCreationException {
        try {
            PerseaFileReader fileReader = new WordFileReader(this.createTemporaryFile(file));
            String rawText = fileReader.readFile();

            CourtCase courtCase = this.courtCaseDao.getById(caseId);
            // TODO get connected user
            // Change unit test
            User user = this.userDao.getById(1L);

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
                            translatedTag = this.translateCaseTag(tag, courtCase);
                            break;
                        case STATIC:
                            translatedTag = this.translateStaticTag(tag, user);
                            break;
                        case DYNAMIC:
                            translatedTag = this.translateDynamicTag(tag);
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

            return buffer.toString();
        } catch (IOException e) {
            throw new TagCreationException("The file cannot be opened");
        } catch (InvalidFormatException e) {
            throw new TagCreationException("Bad Format");
        } catch (NoResultException e) {
            throw new TagCreationException("Missing target data");
        } catch (InvalidTagException e) {
            throw new TagCreationException("Bad Tag: \n" + e.getMessage());
        }
    }
}
