package be.hers.info.persea.service.courtCase;

import be.hers.info.persea.request.courtCase.CreateCourtCaseRequest;
import be.hers.info.persea.request.courtCase.LinkContributorRequest;

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

    /**
     *
     * @param courtCaseId
     * @param request
     */
    void linkClient(long courtCaseId, LinkContributorRequest request);

    /**
     *
     * @param courtCaseId
     * @param request
     */
    void linkOpposition(long courtCaseId, LinkContributorRequest request);

    /**
     *
     * @param courtCaseId
     * @param request
     */
    void linkLawyer(long courtCaseId, LinkContributorRequest request);
}
