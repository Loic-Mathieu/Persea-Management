package be.hers.info.persea.model.courtCase.caseState;

import be.hers.info.persea.model.courtCase.CourtCase;

import java.util.Date;

public interface CaseState {
    void nextState(CourtCase target, Date date);

    void checkCurrentState(CourtCase target, long maxDaysNumber);
}
