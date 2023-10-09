package ee.iti0302.veebiback.util.exception;



public final class EmailInUseException extends GenericRestException {
    public EmailInUseException(String message) {
        super(message);
    }
}
