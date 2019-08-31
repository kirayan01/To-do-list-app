package model.exceptions;

public class InvalidPriorityLevelException extends IllegalArgumentException {
    public InvalidPriorityLevelException() { }

    public InvalidPriorityLevelException(String msg) {
        super(msg);
    }
}
