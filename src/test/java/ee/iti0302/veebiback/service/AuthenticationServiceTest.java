package ee.iti0302.veebiback.service;

import ee.iti0302.veebiback.dto.RegisterDto;
import ee.iti0302.veebiback.repository.PersonRepository;
import ee.iti0302.veebiback.security.jwt.OAuthJWTManager;
import ee.iti0302.veebiback.service.mapper.PersonMapper;
import ee.iti0302.veebiback.service.mapper.PersonMapperImpl;
import ee.iti0302.veebiback.util.constant.RelationshipStatus;
import ee.iti0302.veebiback.util.exception.ApplicationException;
import ee.iti0302.veebiback.util.validation.CustomValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static ee.iti0302.veebiback.testutil.Util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class AuthenticationServiceTest {
    @Spy
    private PasswordEncoder encoder = new BCryptPasswordEncoder();
    @Mock
    private PersonRepository personRepository;
    @Spy
    private PersonMapper personMapper = new PersonMapperImpl();
    @Spy
    private OAuthJWTManager jwtManager;
    @Spy
    private CustomValidator validator;
    @InjectMocks
    private AuthenticationService authenticationService;

    static RegisterDto correctRegisterDto;

    @BeforeAll
    static void setUp() {
        correctRegisterDto = RegisterDto.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@mock.ee")
                .dateOfBirth(LocalDate.of(2000, 1, 1))
                .password("Cheese")
                .relationshipStatus(getMockStatusCode(RelationshipStatus.REL_STATUS_M).asDto())
                .hometown("Cheesetown")
                .residence("Cheesenburg")
                .workplace("Cheesecorp Ltd.")
                .build();
    }

    @Test
    void registerUser_CorrectRegisterDto_NoExceptionsThrown() {
        given(personRepository.existsByEmailIgnoreCase(correctRegisterDto.getEmail()))
                .willReturn(false);

        assertDoesNotThrow(() -> authenticationService.registerUser(correctRegisterDto));

        var entity = then(personMapper).should().registerDtoToEntity(correctRegisterDto);
        then(personRepository).should().save(entity);
    }

    @Test
    void loginUser() {
    }
}