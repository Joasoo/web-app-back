package ee.iti0302.veebiback.controller;

import ee.iti0302.veebiback.dto.BaseDto;
import ee.iti0302.veebiback.dto.EditProfileDataDto;
import ee.iti0302.veebiback.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/edit")
@RequiredArgsConstructor
public class EditProfileController {
    private final ProfileService service;

    public EditProfileDataDto getProfileData(@RequestParam Long id) {
        return service.getProfileData(id);
    }

    public BaseDto saveProfileData(@RequestParam Long id, @RequestBody EditProfileDataDto dto) {
        return service.updateProfileData(dto, id);
    }
}
