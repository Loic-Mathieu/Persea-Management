package be.hers.info.persea.service.courtCase;

import be.hers.info.persea.request.CreateCourtCaseRequest;

public interface CourtCaseService {
    long createCourtCase(CreateCourtCaseRequest request);
}
