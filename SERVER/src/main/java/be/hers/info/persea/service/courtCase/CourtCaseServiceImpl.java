package be.hers.info.persea.service.courtCase;

import be.hers.info.persea.dao.contributor.ClientDao;
import be.hers.info.persea.dao.contributor.OppositionDao;
import be.hers.info.persea.dao.courtcase.CourtCaseDao;
import be.hers.info.persea.model.contibutor.Client;
import be.hers.info.persea.model.contibutor.Opposition;
import be.hers.info.persea.model.courtCase.CourtCase;
import be.hers.info.persea.request.courtCase.CreateCourtCaseRequest;
import be.hers.info.persea.util.time.PerseaTime;
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

    /**
     * creates a valid case number with given information
     * @param client main client of a case
     * @param opposition main opposition of a case
     * @return {clientName}_{oppositionName}_{date}
     */
    private String generateCaseNumber(Client client, Opposition opposition) {
        String clientName = client.getLastName();
        String oppositionName = opposition.getLastName();

        // Takes three first letters of the names and adds the current date
        return (clientName.length() < 3 ? clientName : clientName.substring(0, 3)).toUpperCase()
                + "_" + (oppositionName.length() < 3 ? oppositionName : oppositionName.substring(0, 3)).toUpperCase()
                + "_" + PerseaTime.getShortFormattedDate();
    }

    @Override
    public long createCourtCase(CreateCourtCaseRequest request) {
        Client client = this.clientDao.getById(request.getMainClient());
        Opposition opposition = this.oppositionDao.getById(request.getMainOpposition());

        CourtCase newCourtCase = new CourtCase(generateCaseNumber(
                client,
                opposition
        ));

        newCourtCase.setMainClientId(client.getId());
        newCourtCase.setMainOppositionId(opposition.getId());

        // Create lists
        newCourtCase.getClients().add(client);
        newCourtCase.getOppositions().add(opposition);

        this.courtCaseDao.addOne(newCourtCase);
        return newCourtCase.getId();
    }

    @Override
    public boolean updateState(long id, String rawDate) {
        CourtCase courtCase = this.courtCaseDao.getById(id);

        Date date = PerseaTime.parseDate(rawDate);
        courtCase.nextState(date);

        this.courtCaseDao.update(id, courtCase);

        return true;
    }
}
