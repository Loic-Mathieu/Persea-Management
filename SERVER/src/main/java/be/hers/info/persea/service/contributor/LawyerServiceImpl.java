package be.hers.info.persea.service.contributor;

import be.hers.info.persea.dao.address.AddressDao;
import be.hers.info.persea.dao.contributor.LawyerDao;
import be.hers.info.persea.exceptions.BadRequestException;
import be.hers.info.persea.model.address.Address;
import be.hers.info.persea.model.contibutor.Gender;
import be.hers.info.persea.model.contibutor.Lawyer;
import be.hers.info.persea.request.contributor.CreateLawyerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "serviceLawyer")
public class LawyerServiceImpl implements LawyerService {

    private final LawyerDao lawyerDao;
    private final AddressDao addressDao;

    @Autowired
    public LawyerServiceImpl(LawyerDao lawyerDao, AddressDao addressDao) {
        assert lawyerDao != null;
        assert addressDao != null;

        this.lawyerDao = lawyerDao;
        this.addressDao = addressDao;
    }

    private boolean isValidLawyer() {
        return true;
    }

    @Override
    public long createLawyer(CreateLawyerRequest body) throws BadRequestException {
        if (!isValidLawyer()) {
            throw new BadRequestException("");
        }

        Lawyer newLawyer = new Lawyer();
        newLawyer.setFirstName(body.getFirstName());
        newLawyer.setLastName(body.getLastName());

        newLawyer.setGender(Gender.findByCode(body.getGender()));

        newLawyer.setMail(body.getMail());
        newLawyer.setPhoneNumber(body.getPhoneNumber());

        Address address = this.addressDao.getById(body.getAddress());
        newLawyer.setAddress(address);

        this.lawyerDao.addOne(newLawyer);
        return newLawyer.getId();
    }
}
