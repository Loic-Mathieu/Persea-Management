package be.hers.info.persea.service.document;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DocumentService {
    String createDocument(MultipartFile file, long caseSeq) throws IOException;
}
