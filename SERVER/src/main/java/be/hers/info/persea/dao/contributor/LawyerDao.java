package be.hers.info.persea.dao.contributor;

import be.hers.info.persea.dao.IDAOCrud;
import be.hers.info.persea.dao.IDAOFilter;
import be.hers.info.persea.filter.Filter;
import be.hers.info.persea.model.contibutor.Lawyer;

public interface LawyerDao extends IDAOCrud<Lawyer>, IDAOFilter<Lawyer> {
    /**
     * 
     * @param filter
     * @return
     */
    long getSize(Filter<Lawyer> filter);
}
