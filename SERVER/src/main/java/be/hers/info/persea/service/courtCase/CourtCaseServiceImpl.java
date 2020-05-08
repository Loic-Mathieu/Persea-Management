package be.hers.info.persea.service.courtCase;

import be.hers.info.persea.dao.contributor.ClientDao;
import be.hers.info.persea.dao.contributor.OppositionDao;
import be.hers.info.persea.dao.courtcase.CourtCaseDao;
import be.hers.info.persea.model.contibutor.Client;
import be.hers.info.persea.model.contibutor.Opposition;
import be.hers.info.persea.model.courtCase.CourtCase;
import be.hers.info.persea.request.CreateCourtCaseRequest;
import be.hers.info.persea.util.time.PerseaDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CourtCaseServiceImpl implements CourtCaseService {

    private final CourtCaseDao courtCaseDao;

    private final ClientDao clientDao;
    private final OppositionDao oppositionDao;

    @Autowired
    public CourtCaseServiceImpl(CourtCaseDao courtCaseDao, ClientDao clientDao, OppositionDao oppositionDao) {
        assert courtCaseDao != null;
        assert clientDao != null;
        assert oppositionDao != null;

        this.courtCaseDao = courtCaseDao;
        this.clientDao = clientDao;
        this.oppositionDao = oppositionDao;
    }

    private String generateCaseNumber(Client client, Opposition opposition, String date) {
        return "PH_" + client.getLastName().toUpperCase();
    }

    @Override
    public long createCourtCase(CreateCourtCaseRequest request) {
        Client client = this.clientDao.getById(request.getMainClient());
        Opposition opposition = this.oppositionDao.getById(request.getMainOpposition());

        CourtCase newCourtCase = new CourtCase(generateCaseNumber(
                client,
                opposition,
                PerseaDate.getStandardFormattedDate()
        ));

        newCourtCase.setMainClientId(client.getId());
        // newCourtCase.setMainOppositionId(opposition.getId());
        newCourtCase.setMainOppositionId(0);

        // newCourtCase.getClients().add(client);
        // newCourtCase.getOppositions().add(opposition);

        this.courtCaseDao.addOne(newCourtCase);
        return newCourtCase.getId();
    }
}
