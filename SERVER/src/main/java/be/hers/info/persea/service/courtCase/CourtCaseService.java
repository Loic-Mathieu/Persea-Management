package be.hers.info.persea.service.courtCase;

import be.hers.info.persea.request.courtCase.CreateCourtCaseRequest;

public interface CourtCaseService {
    long createCourtCase(CreateCourtCaseRequest request);
}
