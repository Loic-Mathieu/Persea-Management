package be.hers.info.persea.service.document;

import be.hers.info.persea.exceptions.TagCreationException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface DocumentService {
    /**
     *
     * @param file
     * @param caseSeq
     * @return
     * @throws TagCreationException
     */
    String createDocument(MultipartFile file, long caseSeq) throws TagCreationException;

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
