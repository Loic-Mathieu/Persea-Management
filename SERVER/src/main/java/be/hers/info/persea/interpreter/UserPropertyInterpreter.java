package be.hers.info.persea.interpreter;

import be.hers.info.persea.model.User;
import be.hers.info.persea.model.document.PerseaProperty;

public class UserPropertyInterpreter implements PropertyInterpreter {

    private final User user;

    public UserPropertyInterpreter(User user) {
        this.user = user;
    }

    @Override
    public String interpret(PerseaProperty property) {
        switch (property) {
            case USER_NAME:
                return user.getLastName();
            default:
                throw new IllegalArgumentException();
        }
    }
}
