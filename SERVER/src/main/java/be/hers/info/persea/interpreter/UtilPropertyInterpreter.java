package be.hers.info.persea.interpreter;

import be.hers.info.persea.model.document.PerseaProperty;
import be.hers.info.persea.util.time.PerseaDate;

public class UtilPropertyInterpreter implements PropertyInterpreter {
    @Override
    public String interpret(PerseaProperty property) {
        switch (property) {
            case CURRENT_DATE:
                return PerseaDate.getStandardFormattedDate();
            default:
                throw new IllegalArgumentException();
        }
    }
}
