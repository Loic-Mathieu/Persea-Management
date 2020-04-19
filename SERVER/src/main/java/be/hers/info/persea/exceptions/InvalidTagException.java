package be.hers.info.persea.exceptions;

import be.hers.info.persea.model.document.PerseaProperty;
import be.hers.info.persea.model.document.Tag;

public class InvalidTagException extends Exception {
    private static String createExceptionMessage(Tag tag, String... infos) {
        if (tag == null) {
            return "Cannot read tag";
        }

        if (tag.getName() == null) {
            return "Cannot read tag name";
        }

        if (tag.getProperty() == null) {
            return "Cannot read property";
        }

        StringBuilder additionalInfos = new StringBuilder();
        for (String info : infos) {
            additionalInfos.append("\n").append(info);
        }

        return "Tag: " + tag.getName() + " - " + tag.getProperty() + " is not valid" + additionalInfos;
    }

    public InvalidTagException(Tag tag) {
        super(InvalidTagException.createExceptionMessage(tag));
    }

    public InvalidTagException(Tag tag, PerseaProperty.PropertyType expectedPropertyType) {
        super(InvalidTagException.createExceptionMessage(
                tag,
                "expected: " + expectedPropertyType.toString(),
                "given: " + tag.getProperty().getPropertyType()
        ));
    }
}
