package be.hers.info.persea.interpreter;

import be.hers.info.persea.model.document.PerseaProperty;
import be.hers.info.persea.util.time.PerseaTime;

public class UtilPropertyInterpreter implements PropertyInterpreter {
    @Override
    public String interpret(PerseaProperty property) {
        switch (property) {
            case CURRENT_DATE:
                return PerseaTime.getStandardFormattedDate();
            default:
                throw new IllegalArgumentException();
        }
    }
}
