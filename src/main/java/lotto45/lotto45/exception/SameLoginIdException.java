package lotto45.lotto45.exception;

public class SameLoginIdException extends RuntimeException {
    public SameLoginIdException() {
        super();
    }

    public SameLoginIdException(String message) {
        super(message);
    }

    public SameLoginIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public SameLoginIdException(Throwable cause) {
        super(cause);
    }
}
