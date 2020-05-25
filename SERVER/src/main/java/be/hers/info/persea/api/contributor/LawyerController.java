package be.hers.info.persea.api.contributor;

import be.hers.info.persea.dao.contributor.LawyerDao;
import be.hers.info.persea.dto.contributor.LawyerDto;
import be.hers.info.persea.filter.contributor.LawyerFilter;
import be.hers.info.persea.model.contibutor.Lawyer;
import be.hers.info.persea.service.contributor.LawyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/lawyers")
@CrossOrigin(origins = "http://localhost:4200")
public class LawyerController {
    private final LawyerDao lawyerDao;
    private final LawyerService lawyerService;

    @Autowired
    public LawyerController(LawyerDao lawyerDao, LawyerService lawyerService) {
        assert lawyerDao != null;
        assert lawyerService != null;

        this.lawyerDao = lawyerDao;
        this.lawyerService = lawyerService;
    }

    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<LawyerDto> getLawyer(@PathVariable long id) {
        try {
            Lawyer lawyer = this.lawyerDao.getById(id);
            return new ResponseEntity<>(new LawyerDto(lawyer), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<LawyerDto>> getLawyers(@ModelAttribute LawyerFilter filter) {
        try {
            List<LawyerDto> oppositions = this.lawyerDao.find(filter).stream()
                    .map(LawyerDto::new)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(oppositions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/size")
    public ResponseEntity<Long> getLawyersNumber(@ModelAttribute LawyerFilter filter) {
        try {
            Long size = this.lawyerDao.getSize(filter);
            return new ResponseEntity<>(size, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
