package be.hers.info.persea.dao;

import be.hers.info.persea.model.LegalRepresentation;

import java.util.List;

public interface LegalRepresentationDao extends IDAOCrud<LegalRepresentation> {
    public List<LegalRepresentation> findByCaseNumber(String caseNumber);
}
