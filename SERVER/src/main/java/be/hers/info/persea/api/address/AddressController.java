package be.hers.info.persea.api.address;

import be.hers.info.persea.dao.address.AddressDao;
import be.hers.info.persea.dto.address.AddressDto;
import be.hers.info.persea.model.address.Address;
import be.hers.info.persea.request.address.CreateAddressRequest;
import be.hers.info.persea.service.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rest/addresses")
@CrossOrigin(origins = "http://localhost:4200")
public class AddressController {

    private final AddressDao addressDao;

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressDao addressDao, AddressService addressService) {
        assert addressDao != null;
        assert addressService != null;

        this.addressDao = addressDao;
        this.addressService = addressService;
    }

    @GetMapping("/{addressId:[0-9]+}")
    public ResponseEntity<AddressDto> getAddress(@PathVariable long addressId) {
        try {
            Address address = this.addressDao.getById(addressId);
            return new ResponseEntity<>(new AddressDto(address), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Long> postAddress(HttpServletRequest request,
                                            @RequestBody CreateAddressRequest body) {
        try {
            long id = this.addressService.createAddress(body);
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(0L, HttpStatus.BAD_REQUEST);
        }
    }
}
