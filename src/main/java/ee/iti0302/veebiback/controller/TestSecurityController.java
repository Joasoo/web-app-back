package ee.iti0302.veebiback.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestSecurityController {

    @GetMapping("/test")
    public Map<String, String> getTest() {
        return Map.of("thing", "test");
    }
}
