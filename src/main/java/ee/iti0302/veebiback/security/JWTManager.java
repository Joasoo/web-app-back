package ee.iti0302.veebiback.security;

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
     * @return True if token is valid.
     */
    boolean validate(@NotNull String token);
}
