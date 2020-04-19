package be.hers.info.persea.model.courtCase.caseState;

public class CaseStateContext {

    private CaseStateContext() {}

    public static CaseState translateState(CaseStateKey stateKey) {
        switch (stateKey) {
            default:
                throw new IllegalArgumentException("Invalid case state");
        }
    }
}
