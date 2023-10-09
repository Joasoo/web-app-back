package ee.iti0302.veebiback.controller;

import ee.iti0302.veebiback.dto.BaseDto;
import ee.iti0302.veebiback.dto.EditProfileDataDto;
import ee.iti0302.veebiback.dto.ProfileDataDto;
import ee.iti0302.veebiback.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/edit")
@RequiredArgsConstructor
public class EditProfileController {
    private final ProfileService service;

    @GetMapping(path="/person")
    public ProfileDataDto getProfileData(@RequestParam Long id) {
        return service.getProfileData(id);
    }

    @GetMapping(path="/profile")
    public EditProfileDataDto getEditProfileDataDto(@PathVariable Long id) {
        return service.getEditViewData(id);
    }

    @PostMapping(path="/save")
    public BaseDto saveProfileData(@RequestParam Long id, @RequestBody EditProfileDataDto dto) {
        return service.updateProfileData(id, dto);
    }
}
