package be.hers.info.persea.api.document;

import be.hers.info.persea.exceptions.TagCreationException;
import be.hers.info.persea.service.document.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.nio.file.Files;
import java.nio.file.Path;

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

    @GetMapping("/{id:[0-9]+}/document/{filename:.+}")
    @ResponseBody
    public void getDocument(HttpServletResponse response,
                            @PathVariable String filename,
                            @PathVariable long id) {
        try {
            Path path = this.documentService.getDocument(id, filename);
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename=" + filename);
            Files.copy(path, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/{id:[0-9]+}/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(HttpServletResponse response,
                                            @PathVariable String filename,
                                            @PathVariable long id) {
        try {
            Resource resource = this.documentService.getFile(id, filename);
            return ResponseEntity.status(HttpStatus.OK).body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
        }
    }
}
