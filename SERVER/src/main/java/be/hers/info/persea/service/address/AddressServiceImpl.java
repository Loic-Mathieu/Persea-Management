package be.hers.info.persea.service.address;

import be.hers.info.persea.dao.address.AddressDao;
import be.hers.info.persea.exceptions.BadRequestException;
import be.hers.info.persea.model.address.Address;
import be.hers.info.persea.request.address.CreateAddressRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "serviceAddress")
public class AddressServiceImpl implements AddressService {

    private final AddressDao addressDao;

    @Autowired
    public AddressServiceImpl(AddressDao addressDao) {
        assert addressDao != null;

        this.addressDao = addressDao;
    }

    private boolean isAddressValid() {
        // Todo implements
        return true;
    }

    @Override
    public long createAddress(CreateAddressRequest body) throws BadRequestException {
        if (!isAddressValid()) {
            throw new BadRequestException("");
        }

        Address newAddress = new Address();
        newAddress.setNumber(body.getNumber());
        newAddress.setStreet(body.getStreet());
        newAddress.setCity(body.getCity());
        newAddress.setPostalCode(body.getPostalCode());
        newAddress.setCountry(body.getCountry());

        if(body.getComplement() != null && !body.getComplement().isEmpty()) {
            newAddress.setComplement(body.getComplement());
        }

        this.addressDao.addOne(newAddress);

        return newAddress.getId();
    }
}
