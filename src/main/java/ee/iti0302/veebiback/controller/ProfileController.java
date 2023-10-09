package ee.iti0302.veebiback.controller;

import ee.iti0302.veebiback.dto.BaseDto;
import ee.iti0302.veebiback.dto.EditProfileDataDto;
import ee.iti0302.veebiback.dto.ViewProfileDataDto;
import ee.iti0302.veebiback.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/{id}")
    public ViewProfileDataDto getViewProfileData(@PathVariable Long id) {
        return profileService.getViewProfileData(id);
    }

    @GetMapping(path="/edit/{id}")
    public EditProfileDataDto getEditProfileData(@PathVariable Long id) {
        return profileService.getEditProfileData(id);
    }

    @PostMapping(path="/edit/save")
    public BaseDto saveProfileData(@RequestBody EditProfileDataDto dto) {
        return profileService.updateProfileData(dto);
    }
}
