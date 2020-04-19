package be.hers.info.persea.service.document;

import be.hers.info.persea.exceptions.TagCreationException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DocumentService {
    String createDocument(MultipartFile file, long caseSeq) throws TagCreationException;
}
