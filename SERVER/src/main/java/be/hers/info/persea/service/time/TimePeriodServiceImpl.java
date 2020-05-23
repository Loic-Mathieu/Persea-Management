package be.hers.info.persea.service.time;

import be.hers.info.persea.dao.courtcase.CourtCaseDao;
import be.hers.info.persea.dao.time.TimePeriodDao;
import be.hers.info.persea.dao.user.UserDao;
import be.hers.info.persea.model.time.PeriodType;
import be.hers.info.persea.model.time.TimePeriod;
import be.hers.info.persea.request.time.CreateTimePeriodRequest;
import be.hers.info.persea.util.time.PerseaTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "serviceTime")
public class TimePeriodServiceImpl implements TimePeriodService {

    private final TimePeriodDao timePeriodDao;
    private final CourtCaseDao courtCaseDao;
    UserDao userDao; // TODO temp

    @Autowired
    public TimePeriodServiceImpl(TimePeriodDao timePeriodDao, CourtCaseDao courtCaseDao, UserDao userDao) {
        assert timePeriodDao != null;
        assert courtCaseDao != null;

        this.timePeriodDao = timePeriodDao;
        this.courtCaseDao = courtCaseDao;
        this.userDao = userDao;
    }

    @Override
    public long creatTimePeriod(CreateTimePeriodRequest request) {
        TimePeriod newTimePeriod = new TimePeriod();

        newTimePeriod.setCourtCase(this.courtCaseDao.getById(request.getCaseId()));
        newTimePeriod.setDescription(request.getDescription());
        // TODO
        newTimePeriod.setPeriodType(PeriodType.OTHER);

        // Dates
        String start = request.getDate() + " " + request.getHourStart() + ":" + request.getMinuteStart();
        newTimePeriod.setStartTime(PerseaTime.parseDate(start, PerseaTime.DATE_HOUR_FORMAT));
        String end = request.getDate() + " " + request.getHourStart() + ":" + request.getMinuteStart();
        newTimePeriod.setStopTime(PerseaTime.parseDate(end, PerseaTime.DATE_HOUR_FORMAT));

        // TODO
        newTimePeriod.setOwner(this.userDao.getById(1));
        newTimePeriod.setBilled(false);

        this.timePeriodDao.addOne(newTimePeriod);

        return newTimePeriod.getId();
    }
}
