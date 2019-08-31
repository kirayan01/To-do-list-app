package model.exceptions;

public class NullArgumentException extends IllegalArgumentException {
    public NullArgumentException() { }

    public NullArgumentException(String msg) {
        super(msg);
    }
}
