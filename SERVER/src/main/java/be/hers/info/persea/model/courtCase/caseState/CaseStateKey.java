package be.hers.info.persea.model.courtCase.caseState;

import lombok.Getter;

public enum CaseStateKey {

    // OPEN
    OPEN(CaseStateGroup.ACTIVE),

    // CLOSED
    WAITING_PAYMENT(CaseStateGroup.CLOSED),
    UNPAID(CaseStateGroup.CLOSED),

    // ARCHIVED
    ARCHIVED(CaseStateGroup.ARCHIVED);

    public enum CaseStateGroup {
        ACTIVE,
        CLOSED,
        ARCHIVED
    }

    @Getter
    private final CaseStateGroup caseStateGroup;

    CaseStateKey(CaseStateGroup caseStateGroup) {
        this.caseStateGroup = caseStateGroup;
    }

    public static CaseStateKey[] getKeysByGroup(CaseStateGroup group) {
        switch (group) {
            case ACTIVE:
                return new CaseStateKey[]{OPEN};
            case CLOSED:
                return new CaseStateKey[]{
                        WAITING_PAYMENT,
                        UNPAID
                };
            case ARCHIVED:
                return new CaseStateKey[]{ARCHIVED};
            default:
                return null;
        }
    }
}
