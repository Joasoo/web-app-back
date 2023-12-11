package ee.iti0302.veebiback.service;

import ee.iti0302.veebiback.dto.BaseDto;
import ee.iti0302.veebiback.dto.LoginDto;
import ee.iti0302.veebiback.dto.RegisterDto;
import ee.iti0302.veebiback.dto.TokenDto;
import ee.iti0302.veebiback.repository.PersonRepository;
import ee.iti0302.veebiback.security.jwt.OAuthJWTManager;
import ee.iti0302.veebiback.util.exception.ApplicationException;
import ee.iti0302.veebiback.util.exception.AuthenticationException;
import ee.iti0302.veebiback.service.mapper.PersonMapper;
import ee.iti0302.veebiback.util.validation.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static ee.iti0302.veebiback.util.constant.ExceptionMessageConstant.EMAIL_IN_USE;
import static ee.iti0302.veebiback.util.constant.ExceptionMessageConstant.EMAIL_OR_PASSWORD_INCORRECT;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder encoder;
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final OAuthJWTManager jwtManager;
    private final CustomValidator validator;

    public BaseDto registerUser(RegisterDto dto) {
        validator.validateWithThrow(dto);
        if (accountWithEmailExists(dto.getEmail())) {
            throw new ApplicationException(EMAIL_IN_USE);
        }
        var passwordHash = encoder.encode(dto.getPassword());
        var entity = personMapper.registerDtoToEntity(dto);

        entity.setPassword(passwordHash);
        personRepository.save(entity);
        return new BaseDto();
    }

    public TokenDto loginUser(LoginDto dto) {
        var person = personRepository.findByEmailIgnoreCase(dto.getEmail());
        if (person.isEmpty()
                || !encoder.matches(dto.getPassword(), person.get().getPassword())) {
            throw new AuthenticationException(EMAIL_OR_PASSWORD_INCORRECT);
        }
        return new TokenDto(person.get().getId(), jwtManager.generate(person.get().getId()));
    }

    private boolean accountWithEmailExists(String email) {
        return personRepository.existsByEmailIgnoreCase(email);
    }
}
