package be.hers.info.persea.api.representation;

import be.hers.info.persea.dao.legalrepresentation.LegalRepresentationDao;
import be.hers.info.persea.dto.representation.LegalRepresentationDto;
import be.hers.info.persea.model.representation.LegalRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/representations")
public class RepresentationController {

    private final LegalRepresentationDao legalRepresentationDao;

    public RepresentationController(LegalRepresentationDao legalRepresentationDao) {
        assert legalRepresentationDao != null;

        this.legalRepresentationDao = legalRepresentationDao;
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

}
