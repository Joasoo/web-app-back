package ee.iti0302.veebiback.service;

import ee.iti0302.veebiback.repository.PersonRepository;
import ee.iti0302.veebiback.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final PersonRepository personRepository;
    private final PostRepository postRepository;


}
