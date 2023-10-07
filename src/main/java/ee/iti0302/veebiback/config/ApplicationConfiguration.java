package ee.iti0302.veebiback.config;

import ee.iti0302.veebiback.security.JWTManager;
import ee.iti0302.veebiback.security.OAuthJWTManager;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfiguration {
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
    public JWTManager getJwtManager() {

        return new OAuthJWTManager(null);
    }
}
