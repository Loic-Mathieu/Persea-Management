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
        for (Gender gender : values()) {
            if (gender.code == code) {
                return gender;
            }
        }

        // None found => default
        return Gender.OTHER;
    }
}
