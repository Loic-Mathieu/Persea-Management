package be.hers.info.persea.interpreter;

import be.hers.info.persea.model.document.PerseaProperty;

public interface PropertyInterpreter {
    String interpret(PerseaProperty property);
}
