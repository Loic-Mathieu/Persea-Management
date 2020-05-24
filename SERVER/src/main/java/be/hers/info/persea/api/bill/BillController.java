package be.hers.info.persea.api.bill;

import be.hers.info.persea.dao.bill.BillDao;
import be.hers.info.persea.dto.bill.BillDto;
import be.hers.info.persea.filter.bill.BillFilter;
import be.hers.info.persea.request.bill.CreateBillRequest;
import be.hers.info.persea.service.billing.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/bills")
@CrossOrigin(origins = "*")
public class BillController {
    private final BillingService billingService;
    private final BillDao billDao;

    @Autowired
    public BillController(BillingService billingService, BillDao billDao) {
        assert billDao != null;
        assert billingService != null;

        this.billDao = billDao;
        this.billingService = billingService;
    }

    @GetMapping("")
    public ResponseEntity<List<BillDto>> getByIds(@ModelAttribute BillFilter filter) {
        try {
            List<BillDto> bills = this.billDao.find(filter).stream()
                    .map(BillDto::new)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(bills, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("")
    public ResponseEntity<Long> createBill(@RequestBody CreateBillRequest body) {
        try {
            long id = this.billingService.createBill(body);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(0L, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/byIds")
    public ResponseEntity<List<BillDto>> getByIds(@ModelAttribute List<Long> ids) {
        try {
            List<BillDto> bills = this.billDao.findByIds(ids).stream()
                    .map(BillDto::new)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(bills, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
