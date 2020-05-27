package be.hers.info.persea.model.courtCase.caseState;

import be.hers.info.persea.model.courtCase.CourtCase;

import java.time.temporal.ChronoUnit;
import java.util.Date;

public class StateClosed implements CaseState {
    @Override
    public void nextState(CourtCase target, Date date) {
        target.setPaymentDate(date);
        target.setStateType(CaseStateKey.ARCHIVED);
        target.setState(CaseStateContext.getInstance().translateState(CaseStateKey.ARCHIVED));
    }

    @Override
    public void checkCurrentState(CourtCase target, long maxDaysNumber) {
        long daysBetween = ChronoUnit.DAYS.between(
                target.getCloseDate().toInstant(),
                new Date().toInstant()
        );
        if (daysBetween >= maxDaysNumber) {
            target.setStateType(CaseStateKey.UNPAID);
            target.setState(CaseStateContext.getInstance().translateState(CaseStateKey.UNPAID));
        }
    }
}
