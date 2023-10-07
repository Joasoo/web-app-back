package ee.iti0302.veebiback.service.authentication.exception;


public class EmailInUseException extends RuntimeException {
    public EmailInUseException(String message) {
        super(message);
    }
}
