package be.hers.info.persea.api.time;

import be.hers.info.persea.dao.time.TimePeriodDao;
import be.hers.info.persea.request.time.CreateTimePeriodRequest;
import be.hers.info.persea.service.time.TimePeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rest/timePeriods")
@CrossOrigin(origins = "*")
public class TimePeriodController {
    private final TimePeriodDao timePeriodDao;
    private final TimePeriodService timePeriodService;

    @Autowired
    public TimePeriodController(TimePeriodDao timePeriodDao, TimePeriodService timePeriodService) {
        assert timePeriodDao != null;
        assert timePeriodService != null;

        this.timePeriodDao = timePeriodDao;
        this.timePeriodService = timePeriodService;
    }

    @PostMapping("")
    public ResponseEntity<Long> createTimePeriod(HttpServletRequest request,
                                                 @RequestBody CreateTimePeriodRequest body) {
        try {
            long id = this.timePeriodService.creatTimePeriod(body);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(0L, HttpStatus.BAD_REQUEST);
        }
    }
}
