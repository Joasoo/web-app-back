package ee.iti0302.veebiback.service;

import ee.iti0302.veebiback.domain.Friendship;
import ee.iti0302.veebiback.domain.Person;
import ee.iti0302.veebiback.domain.Post;
import ee.iti0302.veebiback.dto.*;
import ee.iti0302.veebiback.repository.FriendshipRepository;
import ee.iti0302.veebiback.service.mapper.PostMapper;
import ee.iti0302.veebiback.repository.PersonRepository;
import ee.iti0302.veebiback.repository.PostRepository;
import ee.iti0302.veebiback.service.mapper.StatusCodeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PersonRepository personRepository;
    private final PostMapper postMapper;
    private final FriendshipRepository friendshipRepository;
    private final StatusCodeMapper statusCodeMapper;

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

    public List<PostDto> getFeed(Long personId, Integer page, Integer limit) {
        List<Long> friends = getFriendIds(personId);
        Pageable query = PageRequest.of(page, limit, Sort.by("createdAt").descending());
        List<Post> posts = postRepository.findAllByPersonIdIn(friends, query);
        return postMapper.toDtoList(posts);
    }


    private List<Long> getFriendIds(Long personId) {
        String temp = "Accepted";
        List<Friendship> friends = friendshipRepository.findFriendshipsByPersonIdAndStatusValue(personId, temp);
        return friends.stream()
                .map(fs -> fs.getFriend().getId())
                .toList();
    }

}
