package ee.iti0302.veebiback.service;

import ee.iti0302.veebiback.dto.RegisterDto;
import ee.iti0302.veebiback.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder encoder;
    private final PersonRepository personRepository;

    public void registerUser(RegisterDto dto) {
        if (accountWithEmailExists(dto.getEmail())) {
            /* todo send exception 'account with this e-mail exists.' */
            throw new RuntimeException("This e-mail is already in use.");
        }



    }

    private boolean accountWithEmailExists(String email) {
        return personRepository.existsByEmailIgnoreCase(email);
    }
}
