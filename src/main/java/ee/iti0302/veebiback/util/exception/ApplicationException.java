package ee.iti0302.veebiback.util.exception;

public class ApplicationException extends RuntimeException {

    public ApplicationException() {
    }
    public ApplicationException(String message) {
        super(message);
    }
}
