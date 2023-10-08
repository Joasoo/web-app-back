package ee.iti0302.veebiback.service;

import ee.iti0302.veebiback.domain.Person;
import ee.iti0302.veebiback.domain.Post;
import ee.iti0302.veebiback.dto.AddPostDto;
import ee.iti0302.veebiback.dto.BaseDto;
import ee.iti0302.veebiback.dto.PostDto;
import ee.iti0302.veebiback.mapper.PostMapper;
import ee.iti0302.veebiback.repository.PersonRepository;
import ee.iti0302.veebiback.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PersonRepository personRepository;
    private final PostMapper postMapper;

    public List<PostDto> getPostList(Long personId) {
        List<Post> postList =
                postRepository.findAllByPersonIdOrderByCreatedAtDesc(personId);
        return postMapper.toDtoList(postList);
    }

    public BaseDto addPost(AddPostDto postDto) {
        Optional<Person> optionalPerson = personRepository.findById(postDto.getId());
        if (optionalPerson.isEmpty()) {
            // TODO Verify post owner
            return new BaseDto();
        }
        Person person = optionalPerson.get();
        Post newPost = new Post();
        newPost.setContent(postDto.getContent());
        newPost.setPerson(person);
        postRepository.save(newPost);
        return new BaseDto();
    }

    public BaseDto deletePost(Long id) {
        postRepository.deleteById(id);
        return new BaseDto();
    }
}
