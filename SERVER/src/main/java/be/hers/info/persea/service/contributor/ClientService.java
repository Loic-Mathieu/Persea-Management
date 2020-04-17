package be.hers.info.persea.service.contributor;

import be.hers.info.persea.exceptions.BadRequestException;
import be.hers.info.persea.request.CreateClientRequest;

public interface ClientService {
    long createClient(CreateClientRequest body) throws BadRequestException;
}
