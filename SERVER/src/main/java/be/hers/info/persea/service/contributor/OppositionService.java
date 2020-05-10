package be.hers.info.persea.service.contributor;

import be.hers.info.persea.exceptions.BadRequestException;
import be.hers.info.persea.request.contributor.CreateOppositionRequest;

public interface OppositionService {
    long createOpposition(CreateOppositionRequest body) throws BadRequestException;
}
