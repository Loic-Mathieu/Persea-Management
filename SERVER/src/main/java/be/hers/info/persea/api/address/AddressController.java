package be.hers.info.persea.api.address;

import be.hers.info.persea.dao.address.AddressDao;
import be.hers.info.persea.dto.address.AddressDto;
import be.hers.info.persea.model.address.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/addresses")
public class AddressController {

    private final AddressDao addressDao;

    @Autowired
    public AddressController(AddressDao addressDao) {
        assert addressDao != null;

        this.addressDao = addressDao;
    }

    @GetMapping("/{addressId:[0-9]+}")
    public ResponseEntity<AddressDto> getCourtCasesByUserId(@PathVariable long addressId) {
        try {
            Address address = this.addressDao.getById(addressId);
            return new ResponseEntity<>(new AddressDto(address), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
