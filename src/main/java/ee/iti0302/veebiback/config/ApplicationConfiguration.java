package ee.iti0302.veebiback.config;

import com.auth0.jwt.algorithms.Algorithm;
import ee.iti0302.veebiback.security.jwt.OAuthJWTManager;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfiguration {

    @Value("${app.token.secret}")
    private String secret;

    @Bean
    public PasswordEncoder bCryptEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Validator getValidator() {
        return Validation
                .byDefaultProvider()
                .configure()
                .buildValidatorFactory()
                .getValidator();
    }

    @Bean
    public OAuthJWTManager getJwtManager() {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return new OAuthJWTManager(algorithm);
    }
}
