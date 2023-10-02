package ee.iti0302.veebiback.controller;

import ee.iti0302.veebiback.dto.LoginDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class TestSecurityController {

    @GetMapping("/test")
    public LoginDto getTest() {
        return new LoginDto("test@email.com", "pass123");
    }
}
