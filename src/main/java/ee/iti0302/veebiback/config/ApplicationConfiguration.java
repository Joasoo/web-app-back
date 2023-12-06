package ee.iti0302.veebiback.config;

import com.auth0.jwt.algorithms.Algorithm;
import ee.iti0302.veebiback.security.jwt.OAuthJWTManager;
import ee.iti0302.veebiback.util.validation.CustomValidator;
import ee.iti0302.veebiback.util.validation.CustomValidatorImpl;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
public class ApplicationConfiguration {
    @Value("${app.token.secret}")
    private String secret;

    @Bean
    public PasswordEncoder bCryptEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomValidator getValidator() {
        return new CustomValidatorImpl(Validation
                .byProvider(HibernateValidator.class)
                .configure()
                .buildValidatorFactory()
                .getValidator());
    }

    @Bean
    public OAuthJWTManager getJwtManager() {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return new OAuthJWTManager(algorithm);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
