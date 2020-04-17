package be.hers.info.persea.service.contributor;

import be.hers.info.persea.dao.address.AddressDao;
import be.hers.info.persea.dao.contributor.ClientDao;
import be.hers.info.persea.exceptions.BadRequestException;
import be.hers.info.persea.model.Address;
import be.hers.info.persea.model.contibutor.Client;
import be.hers.info.persea.model.contibutor.Gender;
import be.hers.info.persea.request.CreateClientRequest;
import org.springframework.stereotype.Component;

@Component(value = "serviceClient")
public class ClientServiceImpl implements ClientService {

    private ClientDao clientDao;
    private AddressDao addressDao;

    private boolean isValidClient() {
        // Todo implements method
        return true;
    }

    public ClientServiceImpl(ClientDao clientDao, AddressDao addressDao) {
        assert clientDao != null;
        assert addressDao != null;

        this.clientDao = clientDao;
        this.addressDao = addressDao;
    }

    @Override
    public long createClient(CreateClientRequest body) throws BadRequestException {
        if (!isValidClient()) {
            throw new BadRequestException("");
        }

        Client newClient = new Client();
        newClient.setFirstName(body.getFirstName());
        newClient.setLastName(body.getLastName());

        newClient.setGender(Gender.findByCode(body.getGender()));

        newClient.setMail(body.getMail());
        newClient.setPhoneNumber(body.getPhoneNumber());

        Address address = this.addressDao.getById(body.getAddress());
        newClient.setAddress(address);

        this.clientDao.addOne(newClient);
        return newClient.getId();
    }
}
