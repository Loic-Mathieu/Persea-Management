package be.hers.info.persea.api.time;

import be.hers.info.persea.dao.time.TimePeriodDao;
import be.hers.info.persea.dto.time.TimePeriodDto;
import be.hers.info.persea.filter.time.TimeFilter;
import be.hers.info.persea.model.time.TimePeriod;
import be.hers.info.persea.request.time.CreateTimePeriodRequest;
import be.hers.info.persea.service.time.TimePeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("")
    public ResponseEntity<List<TimePeriodDto>> getAllTimePeriods(@ModelAttribute TimeFilter filter) {
        try {
            List<TimePeriodDto> timePeriods = this.timePeriodDao.find(filter).stream()
                .map(TimePeriodDto::new)
                .collect(Collectors.toList());

            return new ResponseEntity<>(timePeriods, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("")
    public ResponseEntity<Long> createTimePeriod(HttpServletRequest request,
                                                 @RequestBody CreateTimePeriodRequest body) {
        try {
            long id = this.timePeriodService.creatTimePeriod(body);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(0L, HttpStatus.BAD_REQUEST);
        }
    }
}
