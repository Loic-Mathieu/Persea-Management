package be.hers.info.persea.model.courtCase.caseState;

import java.util.HashMap;
import java.util.Map;

public class CaseStateContext {

    private static CaseStateContext instance = null;

    private Map<CaseStateKey, CaseState> caseStateMap;

    private CaseStateContext() {
        this.caseStateMap = new HashMap<>();

        this.caseStateMap.put(CaseStateKey.OPEN, new StateOpen());
        this.caseStateMap.put(CaseStateKey.WAITING_PAYMENT, new StateClosed());
        this.caseStateMap.put(CaseStateKey.UNPAID, new StateUnpaid());
        this.caseStateMap.put(CaseStateKey.ARCHIVED, new StateArchived());
    }

    public static CaseStateContext getInstance() {
        if (instance == null) {
            instance = new CaseStateContext();
        }

        return instance;
    }

    public CaseState translateState(CaseStateKey stateKey) {
        return this.caseStateMap.get(stateKey);
    }
}
