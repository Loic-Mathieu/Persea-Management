package be.hers.info.persea.model.courtCase.caseState;

import be.hers.info.persea.model.courtCase.CourtCase;

import java.util.Date;

public class StateUnpaid implements CaseState {
    @Override
    public void nextState(CourtCase target, Date date) {
        target.setPaymentDate(date);
        target.setStateType(CaseStateKey.ARCHIVED);
        target.setState(CaseStateContext.getInstance().translateState(CaseStateKey.ARCHIVED));
    }

    @Override
    public void checkCurrentState(CourtCase target, long maxDaysNumber) {
        // Is ok
    }
}
