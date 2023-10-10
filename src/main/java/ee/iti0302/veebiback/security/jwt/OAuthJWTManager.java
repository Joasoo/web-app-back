package ee.iti0302.veebiback.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class OAuthJWTManager {

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

    /**
     * Create a new JWT token with personId.
     */
    public String generate(long personId) {
        return jwtBuilder
                .withClaim("personId", personId)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 5000L))
                .withNotBefore(new Date(System.currentTimeMillis() + 1000L))
                .withJWTId(UUID.randomUUID().toString())
                .sign(algorithm);
    }

    /**
     * Will return a decoded token if the token is valid.
     * If the token is invalid will return an empty optional.
     */
    public Optional<DecodedJWT> validate(@NotNull String token) {
        try {
            return Optional.of(jwtVerifier.verify(token));
        } catch (JWTVerificationException e) {
            return Optional.empty();
        }
    }
}
