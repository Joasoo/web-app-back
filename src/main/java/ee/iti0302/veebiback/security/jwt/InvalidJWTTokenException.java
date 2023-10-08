package ee.iti0302.veebiback.security.jwt;

public class InvalidJWTTokenException extends Exception {
    public InvalidJWTTokenException(String message) {
        super(message);
    }
}
