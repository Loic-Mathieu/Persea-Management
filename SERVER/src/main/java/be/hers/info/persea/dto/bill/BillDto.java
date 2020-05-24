package be.hers.info.persea.dto.bill;

import be.hers.info.persea.model.bill.Bill;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BillDto {

    private final long id;

    private final String billNumber;

    private final boolean paid;
    private final List<Long> timePeriods;
    private final long courtCase;

    private final long client;

    private final double basePrice;
    private final float rate;
    private final double totalPrice;

    public BillDto(Bill bill) {
        this.id = bill.getId();

        this.billNumber = bill.getBillNumber();

        this.paid = bill.isPaid();
        this.timePeriods = new ArrayList<>();
        bill.getTimePeriods().forEach(timePeriod -> timePeriods.add(timePeriod.getId()));
        this.courtCase = bill.getTimePeriods().get(0).getCourtCase().getId();

        this.client = bill.getRefClient();

        this.basePrice = bill.getBasePrice();
        this.rate = bill.getRate();
        this.totalPrice = bill.getTotalPrice();
    }
}
