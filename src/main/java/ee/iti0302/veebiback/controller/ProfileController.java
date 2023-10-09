package ee.iti0302.veebiback.controller;

import ee.iti0302.veebiback.dto.ProfileDataDto;
import ee.iti0302.veebiback.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService service;

    @GetMapping
    public ProfileDataDto getProfileData(@RequestParam Long id) {
        return service.getProfileData(id);
    }
}
