package be.hers.info.persea.dao.address;

import be.hers.info.persea.model.Address;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "daoAddress")
public class AddressDaoImpl implements AddressDao {
    @Override
    public void addOne(Address newElement) {

    }

    @Override
    public void addAll(List<Address> newElements) {

    }

    @Override
    public Address getById(long id) {
        return null;
    }

    @Override
    public void remove(long id) {

    }

    @Override
    public void update(long id, Address newElement) {

    }
}
