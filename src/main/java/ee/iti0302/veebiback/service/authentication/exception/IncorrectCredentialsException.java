package ee.iti0302.veebiback.service.authentication.exception;

public class IncorrectCredentialsException extends RuntimeException {
    public IncorrectCredentialsException(String message) {
        super(message);
    }
}
