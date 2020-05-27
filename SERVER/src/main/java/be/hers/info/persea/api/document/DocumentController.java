package be.hers.info.persea.api.document;

import be.hers.info.persea.exceptions.TagCreationException;
import be.hers.info.persea.service.document.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/rest/document")
@CrossOrigin(origins = "http://localhost:4200")
public class DocumentController {

    private DocumentService documentService;

    @Autowired
    DocumentController(DocumentService documentService) {
        assert documentService != null;

        this.documentService = documentService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/generate/doc")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,
                                                   @RequestParam("caseSeq") Long caseSeq) {
        try {
            String text = this.documentService.createDocument(file, caseSeq);
            return ResponseEntity.status(HttpStatus.OK).body(text);
        } catch (TagCreationException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
        }
    }
}
