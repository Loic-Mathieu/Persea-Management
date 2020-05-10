package be.hers.info.persea.api.contributor;

import be.hers.info.persea.dao.contributor.OppositionDao;
import be.hers.info.persea.dto.contributor.OppositionDto;
import be.hers.info.persea.model.contibutor.Opposition;
import be.hers.info.persea.service.contributor.OppositionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/oppositions")
public class OppositionController {

    private final OppositionService oppositionService;

    private final OppositionDao oppositionDao;

    @Autowired
    public OppositionController(OppositionDao oppositionDao, OppositionService oppositionService) {
        assert oppositionDao != null;
        assert oppositionService != null;

        this.oppositionDao = oppositionDao;
        this.oppositionService = oppositionService;
    }

    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<OppositionDto> getClient(@PathVariable long id) {
        Opposition opposition = this.oppositionDao.getById(id);
        return new ResponseEntity<>(new OppositionDto(opposition), HttpStatus.OK);
    }
}
