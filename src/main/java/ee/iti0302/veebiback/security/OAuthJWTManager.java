package ee.iti0302.veebiback.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.UUID;

public class OAuthJWTManager implements JWTManager {

    private final Algorithm algorithm;
    private final JWTCreator.Builder jwtBuilder;

    public OAuthJWTManager(@NotNull Algorithm algorithm) {
        this.algorithm = algorithm;
        this.jwtBuilder = JWT.create()
                .withIssuer("FakeBook Inc.")
                .withSubject("JWT signing token by FakeBook Inc.");
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
    public boolean validate(@NotNull String token) {
        return false;
    }
}
