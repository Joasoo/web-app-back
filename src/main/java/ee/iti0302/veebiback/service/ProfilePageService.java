package ee.iti0302.veebiback.service;

import ee.iti0302.veebiback.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfilePageService {
    private final PersonRepository repository;


}
