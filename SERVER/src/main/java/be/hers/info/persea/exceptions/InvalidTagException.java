package be.hers.info.persea.exceptions;

public class InvalidTagException extends Exception {
    public InvalidTagException(String message) {
        super(message);
    }

    public InvalidTagException() {
        super("Tag not found");
    }
}
