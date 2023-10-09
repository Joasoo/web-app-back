package ee.iti0302.veebiback.controller;

import ee.iti0302.veebiback.dto.BaseDto;
import ee.iti0302.veebiback.dto.LoginDto;
import ee.iti0302.veebiback.dto.RegisterDto;
import ee.iti0302.veebiback.dto.TokenDto;
import ee.iti0302.veebiback.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("login")
    public TokenDto loginUser(@RequestBody LoginDto loginDto) {
        return authenticationService.loginUser(loginDto);
    }
    @PostMapping("register")
    public BaseDto registerUser(@RequestBody RegisterDto dto) {
        return authenticationService.registerUser(dto);
    }
}
