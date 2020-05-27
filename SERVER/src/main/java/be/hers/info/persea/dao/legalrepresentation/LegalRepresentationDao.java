package be.hers.info.persea.dao.legalrepresentation;

import be.hers.info.persea.dao.IDAOCrud;
import be.hers.info.persea.dao.IDAOFilter;
import be.hers.info.persea.filter.Filter;
import be.hers.info.persea.filter.representation.RepresentationFilter;
import be.hers.info.persea.model.representation.LegalRepresentation;

import java.util.List;

public interface LegalRepresentationDao extends IDAOCrud<LegalRepresentation>, IDAOFilter<LegalRepresentation> {
    /**
     *
     * @param body
     * @return
     */
    long getSize(RepresentationFilter body);
}
