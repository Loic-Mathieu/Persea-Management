package be.hers.info.persea.service.representation;

import be.hers.info.persea.dao.courtcase.CourtCaseDao;
import be.hers.info.persea.dao.legalrepresentation.LegalRepresentationDao;
import be.hers.info.persea.dao.time.TimePeriodDao;
import be.hers.info.persea.dao.user.UserDao;
import be.hers.info.persea.exceptions.BadRequestException;
import be.hers.info.persea.model.courtCase.CourtCase;
import be.hers.info.persea.model.representation.LegalRepresentation;
import be.hers.info.persea.model.time.PeriodType;
import be.hers.info.persea.model.time.TimePeriod;
import be.hers.info.persea.request.representation.CreateLegalRepresentationRequest;
import be.hers.info.persea.util.time.PerseaTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component(value = "legalRepresentationService")
public class LegalRepresentationServiceImpl implements LegalRepresentationService {

    private final LegalRepresentationDao legalRepresentationDao;
    private final CourtCaseDao courtCaseDao;
    private final TimePeriodDao timePeriodDao;
    UserDao userDao;

    @Autowired
    public LegalRepresentationServiceImpl(LegalRepresentationDao legalRepresentationDao,
                                          CourtCaseDao courtCaseDao,
                                          TimePeriodDao timePeriodDao,
                                          UserDao userDao) {
        assert legalRepresentationDao != null;
        assert courtCaseDao != null;
        assert timePeriodDao != null;

        this.legalRepresentationDao = legalRepresentationDao;
        this.courtCaseDao = courtCaseDao;
        this.timePeriodDao = timePeriodDao;

        this.userDao = userDao;
    }

    private boolean isRepresentationValid(CreateLegalRepresentationRequest body) {
        return PerseaTime.parseDate(body.getDate()) != null;
    }

    @Override
    @Transactional
    public long createLegalRepresentation(CreateLegalRepresentationRequest request) throws BadRequestException {
        if(!isRepresentationValid(request)) {
            throw new BadRequestException("");
        }

        CourtCase targetCase = this.courtCaseDao.getById(request.getCourtCase());

        // Time period
        TimePeriod representationTimePeriod = new TimePeriod();
        representationTimePeriod.setCourtCase(targetCase);
        representationTimePeriod.setDescription(request.getLocation() + " " + request.getSubject());
        representationTimePeriod.setPeriodType(PeriodType.REPRESENTATION);

        String start = request.getDate() + " " + request.getStartTime();
        representationTimePeriod.setStartTime(PerseaTime.parseDate(start, PerseaTime.DATE_HOUR_FORMAT));
        String stop = request.getDate() + " " + request.getStartTime();
        representationTimePeriod.setStopTime(PerseaTime.parseDate(stop, PerseaTime.DATE_HOUR_FORMAT));

        // TODO
        representationTimePeriod.setOwner(this.userDao.getById(1));
        representationTimePeriod.setBilled(false);

        this.timePeriodDao.addOne(representationTimePeriod);

        // Representation
        LegalRepresentation newLegalRepresentation = new LegalRepresentation();
        newLegalRepresentation.setDate(PerseaTime.parseDate(request.getDate()));
        newLegalRepresentation.setLocation(request.getLocation());
        newLegalRepresentation.setSubject(request.getSubject());
        newLegalRepresentation.setCourtCase(targetCase);

        this.legalRepresentationDao.addOne(newLegalRepresentation);

        return newLegalRepresentation.getId();
    }
}
