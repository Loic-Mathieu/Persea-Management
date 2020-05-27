package be.hers.info.persea.service.document;

import be.hers.info.persea.exceptions.TagCreationException;
import be.hers.info.persea.model.bill.Bill;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface DocumentService {
    /**
     *
     * @param file
     * @param caseId
     * @return
     * @throws TagCreationException
     */
    String createDocument(MultipartFile file, long caseId) throws TagCreationException;

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
    Resource getFile(long caseId, String fileName);
}
