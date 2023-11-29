package ee.iti0302.veebiback.util.exception;

public class AuthenticationException extends ApplicationException {
    public AuthenticationException() {
    }

    public AuthenticationException(String message) {
        super(message);
    }
}
