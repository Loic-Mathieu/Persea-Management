package be.hers.info.persea.service.contributor;

import be.hers.info.persea.dao.address.AddressDao;
import be.hers.info.persea.dao.contributor.OppositionDao;
import be.hers.info.persea.exceptions.BadRequestException;
import be.hers.info.persea.model.address.Address;
import be.hers.info.persea.model.contibutor.Gender;
import be.hers.info.persea.model.contibutor.Opposition;
import be.hers.info.persea.request.contributor.CreateOppositionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "serviceOpposition")
public class OppositionServiceImpl implements OppositionService {
    private final OppositionDao oppositionDao;

    private final AddressDao addressDao;

    @Autowired
    public OppositionServiceImpl(OppositionDao oppositionDao, AddressDao addressDao) {
        assert oppositionDao != null;
        assert addressDao != null;

        this.addressDao = addressDao;
        this.oppositionDao = oppositionDao;
    }

    private boolean isValidOpposition() {
        // TODO implements
        return true;
    }

    @Override
    public long createOpposition(CreateOppositionRequest body)  throws BadRequestException {
        if (!isValidOpposition()) {
            throw new BadRequestException("");
        }

        Opposition newOpposition = new Opposition();
        newOpposition.setFirstName(body.getFirstName());
        newOpposition.setLastName(body.getLastName());

        newOpposition.setGender(Gender.findByCode(body.getGender()));

        newOpposition.setMail(body.getMail());
        newOpposition.setPhoneNumber(body.getPhoneNumber());

        Address address = this.addressDao.getById(body.getAddress());
        newOpposition.setAddress(address);

        this.oppositionDao.addOne(newOpposition);
        return newOpposition.getId();
    }
}
