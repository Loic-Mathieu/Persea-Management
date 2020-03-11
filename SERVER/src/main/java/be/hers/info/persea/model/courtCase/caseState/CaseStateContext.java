package be.hers.info.persea.model.courtCase.caseState;

public class CaseStateContext {

    private CaseStateContext() {}

    public static CaseState translateState(CaseStateType stateType) {
        switch (stateType) {
            case Open:
                return null;
            case Closed:
                return null;
            case Archived:
                return null;
        }

        throw new IllegalArgumentException("Invalid case state");
    }
}
