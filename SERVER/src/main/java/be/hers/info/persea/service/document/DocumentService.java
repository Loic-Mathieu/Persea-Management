package be.hers.info.persea.service.document;

import be.hers.info.persea.exceptions.TagCreationException;
import be.hers.info.persea.model.bill.Bill;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

public interface DocumentService {
    /**
     *
     * @param file
     * @param fileName
     * @param caseId
     * @return
     * @throws TagCreationException
     */
    String createDocument(MultipartFile file, String fileName, long caseId) throws TagCreationException;

    /**
     *
     * @param file
     * @param fileName
     * @param caseId
     * @return
     */
    String uploadFile(MultipartFile file, String fileName, long caseId) throws IOException;

    /**
     *
     * @param bill
     * @param caseId
     * @return
     */
    String createBill(Bill bill, long caseId) throws IOException;

    /**
     *
     * @param caseId
     * @return
     */
    Path getZip(long caseId) throws IOException;

    /**
     *
     * @param caseId
     * @param fileName
     * @return
     */
    Path getDocument(long caseId, String fileName);

    /**
     *
     * @param caseId
     * @param fileName
     * @return
     */
    Path getBill(long caseId, String fileName);
}
