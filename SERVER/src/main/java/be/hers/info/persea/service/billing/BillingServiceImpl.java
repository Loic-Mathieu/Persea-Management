package be.hers.info.persea.service.billing;

import be.hers.info.persea.dao.bill.BillDao;
import be.hers.info.persea.dao.time.TimePeriodDao;
import be.hers.info.persea.model.bill.Bill;
import be.hers.info.persea.model.courtCase.CourtCase;
import be.hers.info.persea.model.time.TimePeriod;
import be.hers.info.persea.request.bill.CreateBillRequest;
import be.hers.info.persea.util.time.PerseaTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component(value = "serviceBill")
public class BillingServiceImpl implements BillingService {
    private final BillDao billDao;
    private final TimePeriodDao timePeriodDao;

    @Autowired
    public BillingServiceImpl(BillDao billDao, TimePeriodDao timePeriodDao) {
        assert billDao != null;
        assert timePeriodDao != null;

        this.billDao = billDao;
        this.timePeriodDao = timePeriodDao;
    }

    private boolean isValidBill(CreateBillRequest request) {
        if (request.getRate() > 1) {
            return false;
        }

        return true;
    }

    private String createBillNumber(CourtCase courtCase) {
        long number = this.billDao.getLastNumber(courtCase.getId()) + 1;
        return courtCase.getCaseNumber()
                + "__"
                + PerseaTime.getShortFormattedDate()
                + "_"
                + number;
    }

    @Override
    @Transactional
    public long createBill(CreateBillRequest request) {
        if (!isValidBill(request)) {
            throw new IllegalArgumentException();
        }

        List<TimePeriod> timePeriods = this.timePeriodDao.findByIds(request.getTimePeriods());

        Bill newBill = new Bill();
        // TODO
        newBill.setBasePrice(1);
        newBill.setBillNumber(this.createBillNumber(timePeriods.get(0).getCourtCase()));
        newBill.setRate(request.getRate());
        newBill.setPaid(false);
        newBill.setTimePeriods(timePeriods);
        newBill.setRefClient(request.getClientId());

        timePeriods.forEach(timePeriod -> {
            timePeriod.setBilled(true);
            // this.timePeriodDao.update(timePeriod.getId(), timePeriod);
        });
        this.billDao.addOne(newBill);

        return newBill.getId();
    }
}
