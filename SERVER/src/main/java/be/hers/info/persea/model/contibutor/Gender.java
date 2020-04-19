package be.hers.info.persea.model.contibutor;

public enum Gender {
    MALE(0),
    FEMALE(1),
    OTHER(2);

    public final int code;

    Gender(int code) {
        this.code = code;
    }

    public static Gender findByCode(int code) {
        switch (code) {
            case 0:
                return MALE;
            case 1:
                return FEMALE;
            default:
                return OTHER;
        }
    }
}
