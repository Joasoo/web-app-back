package ee.iti0302.veebiback.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.UUID;

public class OAuthJWTManager implements JWTManager {

    private final Algorithm algorithm;
    private final JWTCreator.Builder jwtBuilder;
    private final JWTVerifier jwtVerifier;

    public OAuthJWTManager(@NotNull Algorithm algorithm) {
        this.algorithm = algorithm;
        this.jwtBuilder = JWT.create()
                .withIssuer("FakeBook Inc.")
                .withSubject("JWT signing token by FakeBook Inc.");
        this.jwtVerifier = JWT.require(algorithm).build();
    }

    @Override
    public String generate(long userId) {
        return jwtBuilder
                .withClaim("userId", userId)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 5000L))
                .withNotBefore(new Date(System.currentTimeMillis() + 1000L))
                .withJWTId(UUID.randomUUID().toString())
                .sign(algorithm);
    }

    @Override
    public JWTToken validate(@NotNull String token) throws InvalidJWTTokenException {
        try {
            var decodedToken = jwtVerifier.verify(token);
            return new OAuthJWTToken(
                    decodedToken.getHeader(),
                    decodedToken.getPayload(),
                    decodedToken.getSignature()
            );
        } catch (JWTVerificationException e) {
            throw new InvalidJWTTokenException(e.getMessage());
        }
    }
}
