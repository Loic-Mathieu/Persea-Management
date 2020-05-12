package be.hers.info.persea.api.representation;

import be.hers.info.persea.dao.legalrepresentation.LegalRepresentationDao;
import be.hers.info.persea.dto.representation.LegalRepresentationDto;
import be.hers.info.persea.filter.representation.RepresentationFilter;
import be.hers.info.persea.model.representation.LegalRepresentation;
import be.hers.info.persea.request.representation.CreateLegalRepresentationRequest;
import be.hers.info.persea.service.representation.LegalRepresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/representations")
public class RepresentationController {

    private final LegalRepresentationDao legalRepresentationDao;

    private final LegalRepresentationService legalRepresentationService;

    @Autowired
    public RepresentationController(LegalRepresentationDao legalRepresentationDao,
                                    LegalRepresentationService legalRepresentationService) {
        assert legalRepresentationDao != null;
        assert legalRepresentationService != null;

        this.legalRepresentationService = legalRepresentationService;
        this.legalRepresentationDao = legalRepresentationDao;
    }

    @GetMapping("")
    public ResponseEntity<List<LegalRepresentationDto>> test(@ModelAttribute RepresentationFilter body) {
        try {
            List<LegalRepresentationDto> legalRepresentations =
                    this.legalRepresentationDao.find(body).stream()
                            .map(LegalRepresentationDto::new)
                            .collect(Collectors.toList());
            return new ResponseEntity<>(legalRepresentations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{representationId:[0-9]+}")
    public ResponseEntity<LegalRepresentationDto> getLegalRepresentation(@PathVariable long representationId) {
        try {
            LegalRepresentation legalRepresentation = this.legalRepresentationDao.getById(representationId);
            return new ResponseEntity<>(new LegalRepresentationDto(legalRepresentation), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Long> postLegalRepresentation(HttpServletRequest request,
                                                        @RequestBody CreateLegalRepresentationRequest body) {
        try {
            long id = this.legalRepresentationService.createLegalRepresentation(body);
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(0L, HttpStatus.BAD_REQUEST);
        }
    }

}
