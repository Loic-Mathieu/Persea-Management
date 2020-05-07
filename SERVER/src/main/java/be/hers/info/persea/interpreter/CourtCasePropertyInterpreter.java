package be.hers.info.persea.interpreter;

import be.hers.info.persea.model.courtCase.CourtCase;
import be.hers.info.persea.model.document.PerseaProperty;

public class CourtCasePropertyInterpreter implements PropertyInterpreter {

    private final CourtCase model;

    public CourtCasePropertyInterpreter(CourtCase courtCase) {
        this.model = courtCase;
    }
    
    @Override
    public String interpret(PerseaProperty property) {
        switch (property) {
            case CASE_NUMBER:
                return model.getCaseNumber();
            default:
                throw new IllegalArgumentException();
        }
    }
}
