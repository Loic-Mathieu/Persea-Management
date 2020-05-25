package be.hers.info.persea.dao.contributor;

import be.hers.info.persea.dao.IDAOCrud;
import be.hers.info.persea.dao.IDAOFilter;
import be.hers.info.persea.filter.contributor.OppositionFilter;
import be.hers.info.persea.model.contibutor.Opposition;

import java.util.List;

public interface OppositionDao extends IDAOCrud<Opposition>, IDAOFilter<Opposition> {
    /**
     *
     * @param ids
     * @return
     */
    List<Opposition> findByIds(List<Long> ids);

    /**
     *
     * @param filter
     * @return
     */
    long getSize(OppositionFilter filter);
}
