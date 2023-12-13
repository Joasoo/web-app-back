package ee.iti0302.veebiback.controller;

import ee.iti0302.veebiback.dto.*;
import ee.iti0302.veebiback.service.ProfileService;
import ee.iti0302.veebiback.service.StatusCodeService;
import ee.iti0302.veebiback.util.constant.StatusCodeClass;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    private final StatusCodeService statusCodeService;

    @GetMapping("/{id}")
    public ViewProfileDataDto getViewProfileData(@PathVariable Long id) {
        return profileService.getViewProfileData(id);
    }

    @GetMapping("/edit/{id}")
    public EditProfileDataDto getEditProfileData(@PathVariable Long id) {
        return profileService.getEditProfileData(id);
    }

    @PostMapping("/edit")
    public BaseDto saveProfileData(@RequestBody EditProfileDataDto dto) {
        return profileService.updateProfileData(dto);
    }

    @GetMapping("/relationship-status")
    public List<StatusCodeDto> getAllRelationshipStatuses() {
        return statusCodeService.getStatusCodesByClass(StatusCodeClass.REL_STATUS);
    }

    @GetMapping("/name/{id}")
    public FullNameDto getPersonName(@PathVariable Long id) {
        return profileService.getPersonName(id);
    }
}
