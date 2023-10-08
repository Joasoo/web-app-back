package ee.iti0302.veebiback.security.jwt;

public final class OAuthJWTToken implements JWTToken {

    private final String header;
    private final String payload;
    private final String signature;

    public OAuthJWTToken(String header, String payload, String signature) {
        this.header = header;
        this.payload = payload;
        this.signature = signature;
    }

    @Override
    public String getHeader() {
        return header;
    }

    @Override
    public String getPayload() {
        return payload;
    }

    @Override
    public String getSignature() {
        return signature;
    }
}
