package model.exceptions;

public class EmptyStringException extends IllegalArgumentException {

    public EmptyStringException() { }

    public EmptyStringException(String msg) {
        super(msg);
    }

}
