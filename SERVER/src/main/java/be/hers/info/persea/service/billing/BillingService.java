package be.hers.info.persea.service.billing;

import be.hers.info.persea.request.bill.CreateBillRequest;

import java.io.IOException;

public interface BillingService {
    /**
     *
     * @param request
     * @return
     */
    long createBill(CreateBillRequest request) throws IOException;
}
