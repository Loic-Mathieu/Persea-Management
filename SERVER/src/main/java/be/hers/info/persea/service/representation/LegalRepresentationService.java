package be.hers.info.persea.service.representation;

import be.hers.info.persea.exceptions.BadRequestException;
import be.hers.info.persea.request.representation.CreateLegalRepresentationRequest;

public interface LegalRepresentationService {
    /**
     *
     * @param body
     * @return
     * @throws BadRequestException
     */
    long createLegalRepresentation(CreateLegalRepresentationRequest body) throws BadRequestException;
}
