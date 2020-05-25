package be.hers.info.persea.service.courtCase;

import be.hers.info.persea.request.courtCase.CreateCourtCaseRequest;

public interface CourtCaseService {
    /**
     *
     * @param request
     * @return
     */
    long createCourtCase(CreateCourtCaseRequest request);

    /**
     *
     * @param id
     * @param rawDate
     * @return
     */
    boolean updateState(long id, String rawDate);
}
