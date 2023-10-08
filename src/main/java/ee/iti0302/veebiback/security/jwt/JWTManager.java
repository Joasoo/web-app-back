package ee.iti0302.veebiback.security.jwt;

import jakarta.validation.constraints.NotNull;

public interface JWTManager {

    /**
     * Generate a JWT token.
     * @param userId Person/user ID to include in the token.
     * @return JWT token.
     */
    String generate(long userId);

    /**
     * Validate the given JWT token.
     * @param token Token
     * @return The decoded JWT token if it's valid.
     */
    JWTToken validate(@NotNull String token) throws InvalidJWTTokenException;
}
