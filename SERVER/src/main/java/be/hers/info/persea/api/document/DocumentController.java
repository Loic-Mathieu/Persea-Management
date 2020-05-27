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
@RequestMapping("/document")
@CrossOrigin(origins = "http://localhost:4200")
public class DocumentController {

    private DocumentService documentService;

    @Autowired
    DocumentController(DocumentService documentService) {
        assert documentService != null;

        this.documentService = documentService;
    }

    @PostMapping("/{id:[0-9]+}/document/")
    public ResponseEntity<String> uploadSchematic(@RequestParam("file") MultipartFile file,
                                                  @RequestParam("fileName") String fileName,
                                                  @PathVariable long id) {
        try {
            String finalFileName = this.documentService.createDocument(file, fileName, id);
            return ResponseEntity.status(HttpStatus.OK).body(finalFileName);
        } catch (TagCreationException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
        }
    }

    @PostMapping("/{id:[0-9]+}")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam("fileName") String fileName,
                                             @PathVariable long id) {
        try {
            String finalFileName = this.documentService.uploadFile(file, fileName, id);
            return ResponseEntity.status(HttpStatus.OK).body(finalFileName);
        } catch (Exception e) {
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
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/{id:[0-9]+}/bill/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(HttpServletResponse response,
                                            @PathVariable String filename,
                                            @PathVariable long id) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
    }

    @GetMapping("/{id:[0-9]+}")
    @ResponseBody
    public void getZip(HttpServletResponse response,
                       @PathVariable long id) {
        try {
            Path path = this.documentService.getZip(id);
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=download.zip");
            Files.copy(path, response.getOutputStream());
            response.getOutputStream().flush();
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
