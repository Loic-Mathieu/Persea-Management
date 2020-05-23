package be.hers.info.persea.dao.time;

import be.hers.info.persea.dao.IDAOCrud;
import be.hers.info.persea.dao.IDAOFilter;
import be.hers.info.persea.model.time.TimePeriod;

import java.util.List;

public interface TimePeriodDao extends IDAOCrud<TimePeriod>, IDAOFilter<TimePeriod> {
    /**
     *
     * @param ids
     * @return
     */
    List<TimePeriod> findByIds(List<Long> ids);
}
