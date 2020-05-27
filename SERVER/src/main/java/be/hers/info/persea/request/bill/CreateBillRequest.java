package be.hers.info.persea.request.bill;

import lombok.Getter;

import java.util.List;

@Getter
public class CreateBillRequest {
    private List<Long> timePeriods;

    private float rate;

    private long clientId;
}
