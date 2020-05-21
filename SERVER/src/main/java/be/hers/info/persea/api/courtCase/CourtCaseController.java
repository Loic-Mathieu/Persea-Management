package be.hers.info.persea.api.courtCase;

import be.hers.info.persea.dao.courtcase.CourtCaseDao;
import be.hers.info.persea.dto.courtCase.CourtCaseDto;
import be.hers.info.persea.filter.courtCase.CourtCaseFilter;
import be.hers.info.persea.request.courtCase.CreateCourtCaseRequest;
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
public class CourtCaseController {

    private final CourtCaseDao courtCaseDao;

    private final CourtCaseService courtCaseService;

    @Autowired
    public CourtCaseController(CourtCaseDao courtCaseDao, CourtCaseService courtCaseService) {
        assert courtCaseDao != null;
        assert  courtCaseService != null;

        this.courtCaseDao = courtCaseDao;
        this.courtCaseService = courtCaseService;
    }

    @GetMapping("")
    public ResponseEntity<List<CourtCaseDto>> getCourtCasesByUserId(@ModelAttribute CourtCaseFilter filter) {
        try {
            List<CourtCaseDto> courtCases = this.courtCaseDao.find(filter).stream()
                .map(CourtCaseDto::new)
                .collect(Collectors.toList());

            return new ResponseEntity<>(courtCases, HttpStatus.OK);
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
}
