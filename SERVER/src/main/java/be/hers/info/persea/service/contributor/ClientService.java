package be.hers.info.persea.service.contributor;

import be.hers.info.persea.exceptions.BadRequestException;
import be.hers.info.persea.request.contributor.CreateClientRequest;

public interface ClientService {
    long createClient(CreateClientRequest body) throws BadRequestException;
}
