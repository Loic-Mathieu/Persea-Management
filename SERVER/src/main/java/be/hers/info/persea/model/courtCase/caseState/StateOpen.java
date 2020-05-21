package be.hers.info.persea.model.courtCase.caseState;

import be.hers.info.persea.model.courtCase.CourtCase;

import java.util.Date;

public class StateOpen implements CaseState {
    @Override
    public void nextState(CourtCase target, Date date) {
        target.setCloseDate(date);
        target.setStateType(CaseStateKey.WAITING_PAYMENT);
        target.setState(CaseStateContext.getInstance().translateState(CaseStateKey.WAITING_PAYMENT));
    }

    @Override
    public void checkCurrentState(CourtCase target, long maxDaysNumber) {
        // Is ok
    }
}
