package be.hers.info.persea.service.representation;

import be.hers.info.persea.dao.courtcase.CourtCaseDao;
import be.hers.info.persea.dao.legalrepresentation.LegalRepresentationDao;
import be.hers.info.persea.exceptions.BadRequestException;
import be.hers.info.persea.model.representation.LegalRepresentation;
import be.hers.info.persea.request.representation.CreateLegalRepresentationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component(value = "legalRepresentationService")
public class LegalRepresentationServiceImpl implements LegalRepresentationService {

    private final LegalRepresentationDao legalRepresentationDao;
    private final CourtCaseDao courtCaseDao;

    @Autowired
    public LegalRepresentationServiceImpl(LegalRepresentationDao legalRepresentationDao, CourtCaseDao courtCaseDao) {
        assert legalRepresentationDao != null;
        assert courtCaseDao != null;

        this.legalRepresentationDao = legalRepresentationDao;
        this.courtCaseDao = courtCaseDao;
    }

    private boolean isRepresentationValid() {
        // Todo
        return true;
    }

    @Override
    public long createLegalRepresentation(CreateLegalRepresentationRequest body) throws BadRequestException {

        if(!isRepresentationValid()) {
            throw new BadRequestException("");
        }

        LegalRepresentation newLegalRepresentation = new LegalRepresentation();
        // Todo : format date from string
        newLegalRepresentation.setDate(new Date());
        newLegalRepresentation.setLocation(body.getLocation());
        newLegalRepresentation.setSubject(body.getSubject());
        newLegalRepresentation.setCourtCase(this.courtCaseDao.getById(body.getCourtCase()));

        this.legalRepresentationDao.addOne(newLegalRepresentation);

        return newLegalRepresentation.getId();
    }
}
