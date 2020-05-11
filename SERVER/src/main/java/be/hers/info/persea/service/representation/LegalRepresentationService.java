package be.hers.info.persea.service.representation;

import be.hers.info.persea.exceptions.BadRequestException;
import be.hers.info.persea.request.representation.CreateLegalRepresentationRequest;

public interface LegalRepresentationService {
    long createLegalRepresentation(CreateLegalRepresentationRequest body) throws BadRequestException;
}
