package ee.iti0302.veebiback.service;

import ee.iti0302.veebiback.domain.Person;
import ee.iti0302.veebiback.domain.StatusCode;
import ee.iti0302.veebiback.dto.BaseDto;
import ee.iti0302.veebiback.dto.EditProfileDataDto;
import ee.iti0302.veebiback.dto.ViewProfileDataDto;
import ee.iti0302.veebiback.repository.PersonRepository;
import ee.iti0302.veebiback.repository.StatusCodeRepository;
import ee.iti0302.veebiback.service.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final StatusCodeRepository statusCodeRepository;

    public ViewProfileDataDto getViewProfileData(Long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isEmpty()) {
            return new ViewProfileDataDto();
        }
        Person person = optionalPerson.get();
        return personMapper.entityToProfileDataDto(person);
    }

    public EditProfileDataDto getEditProfileData(Long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            return personMapper.toEditProfileDataDto(person);
        }
        return new EditProfileDataDto();
    }

    public BaseDto updateProfileData(EditProfileDataDto dto) {
        Optional<Person> optionalPerson = personRepository.findById(dto.getId());
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            personMapper.updateProfileDataFromDto(dto, person);
            /* StatusCode instance needs to be set manually, since mapping to
             *  the already 'attached' StatusCode instance using update will result in an exception. */
            if (dto.getRelationshipStatus() == null) {
                person.setRelationshipStatus(null);
            } else {
                Optional<StatusCode> optionalStatusCode = statusCodeRepository.findByCode(
                        dto.getRelationshipStatus().getCode()
                );
                person.setRelationshipStatus(optionalStatusCode.orElseThrow(RuntimeException::new));
            }
            personRepository.save(person);
        }
        return new BaseDto();
    }
}
