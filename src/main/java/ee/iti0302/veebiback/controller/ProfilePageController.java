package ee.iti0302.veebiback.controller;

import ee.iti0302.veebiback.dto.ProfileDataDto;
import ee.iti0302.veebiback.service.ProfilePageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/profile")
@RequiredArgsConstructor
public class ProfilePageController {
    private final ProfilePageService service;
    @GetMapping
    public ProfileDataDto getProfileData(@RequestParam int id) {
        ProfileDataDto testDto = new ProfileDataDto();
        if (id == 1) {
            testDto.setFirstName("Margus");
            testDto.setLastName("Kruus");
            testDto.setWorkplace("Diskmat");
            testDto.setHometown("Tallinn");
            testDto.setResidence("SOC-305");
        }

        if (id == 2) {
            testDto.setFirstName("Liivi");
            testDto.setLastName("Kluge");
            testDto.setWorkplace("Extremely High Maths I");
            testDto.setResidence("U06-103");
        }

        return testDto;
    }
}
