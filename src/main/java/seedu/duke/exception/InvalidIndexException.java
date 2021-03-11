package seedu.duke.exception;

public class InvalidIndexException extends Exception {
    public InvalidIndexException(String message) {
        super(message);
    }

    public InvalidIndexException(String message, String recordNumber) {
        this(recordNumber + " Index - " + message);
    }
}
