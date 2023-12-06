package ee.iti0302.veebiback.service;

import ee.iti0302.veebiback.dto.PersonFullNameDto;
import ee.iti0302.veebiback.repository.PersonRepository;
import ee.iti0302.veebiback.service.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public List<PersonFullNameDto> searchForPeople(String query) {
        var foundPeople = personRepository.searchByFullNameLimit10(query);
        return personMapper.entitiesToPersonFullNameDtos(foundPeople);
    }
}
