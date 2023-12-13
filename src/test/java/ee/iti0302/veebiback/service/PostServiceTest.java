package ee.iti0302.veebiback.service;

import ee.iti0302.veebiback.domain.Person;
import ee.iti0302.veebiback.domain.Post;
import ee.iti0302.veebiback.repository.FriendshipRepository;
import ee.iti0302.veebiback.repository.PersonRepository;
import ee.iti0302.veebiback.repository.PostRepository;
import ee.iti0302.veebiback.service.mapper.PostMapper;
import ee.iti0302.veebiback.service.mapper.PostMapperImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.List;

import static ee.iti0302.veebiback.testutil.Util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class PostServiceTest {
    @Mock
    private PostRepository postRepository;
    @Mock
    private PersonRepository personRepository;
    @Mock
    private FriendshipRepository friendshipRepository;
    @Spy
    private PostMapper postMapper = new PostMapperImpl();
    @InjectMocks
    private PostService service;

    private static Person person1;
    private static Person person2;
    private static Person person3;
    private static Post post1;
    private static Post post2;
    private static Post post3;
    private static Post post4;
    private static Post post5;
    private static Post post6;

    @BeforeAll
    static void setUp() {
        person1 = getMockPerson();
        person2 = getMockPerson(person1.getId());
        person3 = getMockPerson(List.of(person1.getId(), person2.getId()));
        post1 = getMockPost(person1, 1, 1, 2000);
        post2 = getMockPost(person1, 1, 2, 2000, post1.getId());
        post3 = getMockPost(person2, 1, 3, 2000, List.of(post1.getId(), post2.getId()));
        post4 = getMockPost(person2, 1, 4, 2000, List.of(post1.getId(), post2.getId(), post3.getId()));
        post5 = getMockPost(person3, 1, 5, 2000,
                List.of(post1.getId(), post2.getId(), post3.getId(), post4.getId()));
        post6 = getMockPost(person3, 1, 6, 2000,
                List.of(post1.getId(), post2.getId(), post3.getId(), post4.getId(), post5.getId()));
    }

    @Test
    void getPostList() {

    }

    @Test
    void getFeed() {

    }

    @Test
    void addPost() {

    }

    @Test
    void deletePost() {

    }

}