package ee.iti0302.veebiback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class VeebiBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(VeebiBackApplication.class, args);
    }

}
