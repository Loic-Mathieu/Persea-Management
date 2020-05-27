package be.hers.info.persea.service.contributor;

import be.hers.info.persea.exceptions.BadRequestException;
import be.hers.info.persea.request.contributor.CreateLawyerRequest;

public interface LawyerService {
    /**
     *
     * @param body
     * @return
     */
    long createLawyer(CreateLawyerRequest body) throws BadRequestException;
}
