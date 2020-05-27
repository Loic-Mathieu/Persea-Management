package be.hers.info.persea.dao.contributor;

import be.hers.info.persea.dao.IDAOCrud;
import be.hers.info.persea.dao.IDAOFilter;
import be.hers.info.persea.filter.Filter;
import be.hers.info.persea.model.contibutor.Client;

import java.util.List;

public interface ClientDao extends IDAOCrud<Client>, IDAOFilter<Client> {
    /**
     *
     * @param ids
     * @return
     */
    public List<Client> findByIds(List<Long> ids);

    /**
     *
     * @param filter
     * @return
     */
    public long getSize(Filter<Client> filter);
}
