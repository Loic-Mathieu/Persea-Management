package be.hers.info.persea.dao.legalrepresentation;

import be.hers.info.persea.dao.IDAOCrud;
import be.hers.info.persea.model.representation.LegalRepresentation;

import java.util.List;

public interface LegalRepresentationDao extends IDAOCrud<LegalRepresentation> {

    // TODO merge in find
    public List<LegalRepresentation> findByCaseNumber(String caseNumber);

    public List<LegalRepresentation> findByCaseId(long caseId);
}
