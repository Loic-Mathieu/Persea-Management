package be.hers.info.persea.dao;

import be.hers.info.persea.model.courtCase.CourtCase;

import java.util.List;

public interface CourtCaseDao extends IDAOCrud<CourtCase>, IDAOFilter<CourtCase> {
    /**
     *
     * @return
     */
    List<CourtCase> findByUser();
}
