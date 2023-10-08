package ee.iti0302.veebiback.security.jwt;


public interface JWTToken {
    String getHeader();
    String getPayload();
    String getSignature();
}
