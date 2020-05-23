package be.hers.info.persea.dao.bill;

import be.hers.info.persea.dao.IDAOCrud;
import be.hers.info.persea.dao.IDAOFilter;
import be.hers.info.persea.model.bill.Bill;

public interface BillDao extends IDAOCrud<Bill>, IDAOFilter<Bill> {
}
