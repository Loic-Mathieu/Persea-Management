package be.hers.info.persea.dao.bill;

import be.hers.info.persea.dao.IDAOCrud;
import be.hers.info.persea.dao.IDAOFilter;
import be.hers.info.persea.model.bill.Bill;

import java.util.List;

public interface BillDao extends IDAOCrud<Bill>, IDAOFilter<Bill> {
    /**
     *
     * @param ids
     * @return
     */
    List<Bill> findByIds(List<Long> ids);

    /**
     *
     * @param id
     * @return
     */
    long getLastNumber(long id);
}
