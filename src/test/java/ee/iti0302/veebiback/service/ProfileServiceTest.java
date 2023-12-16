package ee.iti0302.veebiback.service;

import ee.iti0302.veebiback.domain.Person;
import ee.iti0302.veebiback.dto.ViewProfileDataDto;
import ee.iti0302.veebiback.repository.PersonRepository;
import ee.iti0302.veebiback.repository.StatusCodeRepository;
import ee.iti0302.veebiback.service.mapper.PersonMapper;
import ee.iti0302.veebiback.service.mapper.PersonMapperImpl;
import ee.iti0302.veebiback.service.mapper.StatusCodeMapper;
import ee.iti0302.veebiback.service.mapper.StatusCodeMapperImpl;
import ee.iti0302.veebiback.util.validation.CustomValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static ee.iti0302.veebiback.testutil.Util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class ProfileServiceTest {
    @Mock
    private PersonRepository personRepository;
    @Mock
    private StatusCodeRepository statusCodeRepository;
    @Spy
    private PersonMapper personMapper = new PersonMapperImpl();
    @InjectMocks
    private ProfileService service;

    private static Person person1;
    private static Person person2;
    private static Person person3;
    @BeforeAll
    static void setUp() {
        person1 = getMockPerson();
        person2 = getMockPerson(person1.getId());
        person3 = getMockPerson(List.of(person1.getId(), person2.getId()));
    }

    @Test
    void getViewProfileData_NoSuchProfile_EmptyDto() {
        long profileId = 2;
        given(personRepository.findById(profileId)).willReturn(Optional.empty());

        ViewProfileDataDto result = service.getViewProfileData(profileId);

        assertEquals(new ViewProfileDataDto(), result);
    }

    @Test
    void getViewProfileData_ProfileExists_FilledDto() {
        given(personRepository.findById(person1.getId())).willReturn(Optional.of(person1));

        ViewProfileDataDto result = service.getViewProfileData(person1.getId());

        then(personMapper).should().entityToProfileDataDto(person1);
        assertEquals(getViewProfileDataDto(person1), result);
    }

    @Test
    void getEditProfileData() {
    }

    @Test
    void updateProfileData() {
    }

    @Test
    void getPersonName() {
    }
}