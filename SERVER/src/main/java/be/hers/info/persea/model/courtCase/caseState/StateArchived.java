package be.hers.info.persea.model.courtCase.caseState;

import be.hers.info.persea.model.courtCase.CourtCase;

import java.util.Date;

public class StateArchived implements CaseState {
    @Override
    public void nextState(CourtCase target, Date date) {
        // Can't Change
    }

    @Override
    public void checkCurrentState(CourtCase target, long maxDaysNumber) {
        // Is ok
    }
}
