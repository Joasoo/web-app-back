package ee.iti0302.veebiback.service.authentication;

import ee.iti0302.veebiback.dto.BaseDto;
import ee.iti0302.veebiback.dto.LoginDto;
import ee.iti0302.veebiback.dto.RegisterDto;
import ee.iti0302.veebiback.repository.PersonRepository;
import ee.iti0302.veebiback.service.authentication.exception.EmailInUseException;
import ee.iti0302.veebiback.service.authentication.exception.IncorrectCredentialsException;
import ee.iti0302.veebiback.service.mapper.PersonMapper;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder encoder;
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final Validator validator; /*todo for later*/

    public BaseDto registerUser(RegisterDto dto) {
        if (accountWithEmailExists(dto.getEmail())) {
            throw new EmailInUseException("This e-mail is already in use.");
        }
        var passwordHash = encoder.encode(dto.getPassword());
        var entity = personMapper.registerDtoToEntity(dto);

        entity.setPassword(passwordHash);
        personRepository.save(entity);
        return new BaseDto();
    }

    public BaseDto loginUser(LoginDto dto) {
        var person = personRepository.findByEmail(dto.getEmail());
        if (person.isEmpty()
                || !encoder.matches(dto.getPassword(), person.get().getPassword())) {
            throw new IncorrectCredentialsException("Incorrect e-mail or password.");
        }
        return new BaseDto();
        /*todo return token.*/
    }

    private boolean accountWithEmailExists(String email) {
        return personRepository.existsByEmailIgnoreCase(email);
    }
}
