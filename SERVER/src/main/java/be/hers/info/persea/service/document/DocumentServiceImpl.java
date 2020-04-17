package be.hers.info.persea.service.document;

import be.hers.info.persea.dao.courtcase.CourtCaseDao;
import be.hers.info.persea.dao.tag.TagDao;
import be.hers.info.persea.documents.PerseaFileReader;
import be.hers.info.persea.documents.word.WordFileReader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component(value = "serviceDocument")
public class DocumentServiceImpl implements DocumentService {

    private TagDao tagDao;
    private CourtCaseDao courtCaseDao;

    @Autowired
    public DocumentServiceImpl(TagDao tagDao, CourtCaseDao courtCaseDao) {
        assert tagDao != null;
        assert  courtCaseDao != null;

        this.tagDao = tagDao;
        this.courtCaseDao = courtCaseDao;
    }

    private File createTemporaryFile(MultipartFile multipartFile, String suffix) throws IOException {
        File tempFile = File.createTempFile("temp", suffix);
        FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.close();
        return tempFile;
    }

    @Override
    public String createDocument(MultipartFile file, long caseId) throws IOException {
        try {
            PerseaFileReader fileReader = new WordFileReader(
                    this.createTemporaryFile(file, WordFileReader.DOCUMENT_SUFFIX)
            );
            String rawText = fileReader.readFile();
            System.out.println("target seq: " + caseId);
            return rawText;
        } catch (InvalidFormatException e) {
            throw new IOException("Bad Format Exception");
        }
    }
}
