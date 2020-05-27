package be.hers.info.persea.api.contributor;

import be.hers.info.persea.dao.contributor.OppositionDao;
import be.hers.info.persea.dto.contributor.ClientDto;
import be.hers.info.persea.dto.contributor.OppositionDto;
import be.hers.info.persea.filter.contributor.OppositionFilter;
import be.hers.info.persea.model.contibutor.Opposition;
import be.hers.info.persea.request.contributor.CreateOppositionRequest;
import be.hers.info.persea.service.contributor.OppositionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/oppositions")
@CrossOrigin(origins = "http://localhost:4200")
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
    public ResponseEntity<OppositionDto> getOpposition(@PathVariable long id) {
        try {
            Opposition opposition = this.oppositionDao.getById(id);
            return new ResponseEntity<>(new OppositionDto(opposition), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<OppositionDto>> getOppositions(@ModelAttribute OppositionFilter filter) {
        try {
            List<OppositionDto> oppositions = this.oppositionDao.find(filter).stream()
                    .map(OppositionDto::new)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(oppositions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/size")
    public ResponseEntity<Long> getSize(@ModelAttribute OppositionFilter filter) {
        try {
            long size = this.oppositionDao.getSize(filter);
            return new ResponseEntity<>(size, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<OppositionDto>> findClients(@RequestParam List<Long> ids) {
        try {
            List<OppositionDto> oppositions = this.oppositionDao.findByIds(ids).stream()
                    .map(OppositionDto::new)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(oppositions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Long> postOpposition(HttpServletRequest request,
                                           @RequestBody CreateOppositionRequest body) {
        try {
            long id = this.oppositionService.createOpposition(body);
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(0L, HttpStatus.BAD_REQUEST);
        }
    }
}
