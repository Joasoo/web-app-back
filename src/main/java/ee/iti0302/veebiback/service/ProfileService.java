package ee.iti0302.veebiback.service;

import ee.iti0302.veebiback.domain.Person;
import ee.iti0302.veebiback.dto.ProfileDataDto;
import ee.iti0302.veebiback.repository.PersonRepository;
import ee.iti0302.veebiback.service.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public ProfileDataDto getProfileData(Long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isEmpty()) {
            return new ProfileDataDto();
        }
        Person person = optionalPerson.get();
        return personMapper.entityToProfileDataDto(person);
    }
}
