package be.hers.info.persea.model.document;

public enum PerseaProperty {

    // CASE RELATED
    CASE_NUMBER(PropertyType.CASE),
    MAIN_CLIENT_NAME(PropertyType.CASE),
    MAIN_CLIENT_FIRST_NAME(PropertyType.CASE),

    // DYNAMIC
    CURRENT_DATE(PropertyType.DYNAMIC),

    // USER
    USER_NAME(PropertyType.USER);

    private final PropertyType propertyType;

    public static enum PropertyType {
        CASE,
        DYNAMIC,
        USER
    }

    PerseaProperty(PropertyType type) {
        this.propertyType = type;
    }

    public PropertyType getPropertyType() {
        return this.propertyType;
    }

}
