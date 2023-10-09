package ee.iti0302.veebiback.controller;

import ee.iti0302.veebiback.dto.BaseDto;
import ee.iti0302.veebiback.dto.EditProfileDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/edit")
@RequiredArgsConstructor
public class EditProfileController {
    private final ProfileService service;

    @GetMapping(path="/person")
    public EditProfileDataDto getProfileData(@RequestParam Long id) {
        return service.getProfileData(id);
    }

    @PostMapping(path="/save")
    public BaseDto saveProfileData(@RequestParam Long id, @RequestBody EditProfileDataDto dto) {
        return service.updateProfileData(dto, id);
    }
}
