package ee.iti0302.veebiback.util.exception;


public final class IncorrectCredentialsException extends GenericRestException {
    public IncorrectCredentialsException(String message) {
        super(message);
    }
}
