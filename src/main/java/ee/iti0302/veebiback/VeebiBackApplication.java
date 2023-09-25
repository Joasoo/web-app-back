package ee.iti0302.veebiback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// Temporarily disable spring Security and datasource.
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class VeebiBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(VeebiBackApplication.class, args);
    }

}
