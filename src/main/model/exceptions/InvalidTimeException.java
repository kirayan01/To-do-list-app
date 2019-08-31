package model.exceptions;

public class InvalidTimeException extends IllegalArgumentException {
    public InvalidTimeException() { }

    public InvalidTimeException(String msg) {
        super(msg);
    }
}
