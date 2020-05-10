package be.hers.info.persea.api.contributor;

import be.hers.info.persea.dao.contributor.OppositionDao;
import be.hers.info.persea.dto.contributor.OppositionDto;
import be.hers.info.persea.model.contibutor.Opposition;
import be.hers.info.persea.request.contributor.CreateOppositionRequest;
import be.hers.info.persea.service.contributor.OppositionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
        try {
            Opposition opposition = this.oppositionDao.getById(id);
            return new ResponseEntity<>(new OppositionDto(opposition), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Long> postClient(HttpServletRequest request,
                                           @RequestBody CreateOppositionRequest body) {
        try {
            long id = this.oppositionService.createOpposition(body);
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(0L, HttpStatus.BAD_REQUEST);
        }
    }
}
