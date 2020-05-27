package be.hers.info.persea.api.courtCase;

import be.hers.info.persea.dao.courtcase.CourtCaseDao;
import be.hers.info.persea.dto.courtCase.CourtCaseDto;
import be.hers.info.persea.dto.time.TimePeriodDto;
import be.hers.info.persea.filter.courtCase.CourtCaseFilter;
import be.hers.info.persea.filter.time.TimeFilter;
import be.hers.info.persea.model.courtCase.CourtCase;
import be.hers.info.persea.request.courtCase.CreateCourtCaseRequest;
import be.hers.info.persea.request.courtCase.LinkContributorRequest;
import be.hers.info.persea.request.courtCase.UpdateCourtCaseState;
import be.hers.info.persea.service.courtCase.CourtCaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/courtCases")
@CrossOrigin(origins = "http://localhost:4200")
public class CourtCaseController {

    private final CourtCaseDao courtCaseDao;

    private final CourtCaseService courtCaseService;

    @Autowired
    public CourtCaseController(CourtCaseDao courtCaseDao, CourtCaseService courtCaseService) {
        assert courtCaseDao != null;
        assert courtCaseService != null;

        this.courtCaseDao = courtCaseDao;
        this.courtCaseService = courtCaseService;
    }

    @GetMapping("")
    public ResponseEntity<List<CourtCaseDto>> getAllCourtCases(@ModelAttribute CourtCaseFilter filter) {
        try {
            List<CourtCaseDto> courtCases = this.courtCaseDao.find(filter).stream()
                .map(CourtCaseDto::new)
                .collect(Collectors.toList());

            return new ResponseEntity<>(courtCases, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/size")
    public ResponseEntity<Long> getAllCourtCasesSize(@ModelAttribute CourtCaseFilter filter) {
        try {
            long size = this.courtCaseDao.getSize(filter);
            return new ResponseEntity<>(size, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(-1L, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<CourtCaseDto> getAllCourtCasesSize(@PathVariable long id) {
        try {
            CourtCaseDto courtCase = new CourtCaseDto(this.courtCaseDao.getById(id));
            return new ResponseEntity<>(courtCase, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id:[0-9]+}/timePeriods")
    public ResponseEntity<List<TimePeriodDto>> getTimePeriodsByCase(@PathVariable long id,
                                                                    @ModelAttribute TimeFilter filter) {
        try {
            CourtCase courtCase = this.courtCaseDao.getById(id);
            List<TimePeriodDto> timePeriods = courtCase.getTimePeriods().stream()
                    .filter(timePeriod -> timePeriod.isBilled() == filter.getBilled())
                    .map(TimePeriodDto::new)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(timePeriods, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("")
    public ResponseEntity<Long> postCourtCase(HttpServletRequest request,
                                              @RequestBody CreateCourtCaseRequest body) {
        try {
            long id = this.courtCaseService.createCourtCase(body);
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return new ResponseEntity<>(0L, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id:[0-9]+}/clients")
    public ResponseEntity<Boolean> addClient(HttpServletRequest request,
                                             @PathVariable long id,
                                             @RequestBody LinkContributorRequest body) {
        try {
            this.courtCaseService.linkClient(id, body);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id:[0-9]+}/oppositions")
    public ResponseEntity<Boolean> addOpposition(HttpServletRequest request,
                                                 @PathVariable long id,
                                                 @RequestBody LinkContributorRequest body) {
        try {
            this.courtCaseService.linkOpposition(id, body);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id:[0-9]+}/lawyers")
    public ResponseEntity<Boolean> addLawyer(HttpServletRequest request,
                                             @PathVariable long id,
                                             @RequestBody LinkContributorRequest body) {
        try {
            this.courtCaseService.linkClient(id, body);
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id:[0-9]+}/state")
    public ResponseEntity<Boolean> postCourtCase(@PathVariable long id,
                                                 @RequestBody UpdateCourtCaseState updateCourtCaseState) {
        try {
            boolean isUpdated = this.courtCaseService.updateState(id, updateCourtCaseState.getRawDate());
            return new ResponseEntity<>(isUpdated, HttpStatus.CREATED);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
}
