package ee.iti0302.veebiback.service;

import ee.iti0302.veebiback.domain.Friendship;
import ee.iti0302.veebiback.domain.Person;
import ee.iti0302.veebiback.domain.Post;
import ee.iti0302.veebiback.domain.StatusCode;
import ee.iti0302.veebiback.dto.PostDto;
import ee.iti0302.veebiback.repository.FriendshipRepository;
import ee.iti0302.veebiback.repository.PersonRepository;
import ee.iti0302.veebiback.repository.PostRepository;
import ee.iti0302.veebiback.service.mapper.PostMapper;
import ee.iti0302.veebiback.service.mapper.PostMapperImpl;
import ee.iti0302.veebiback.util.constant.FriendshipStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static ee.iti0302.veebiback.testutil.Util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
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
    private static StatusCode accepted;
    private static StatusCode sent;
    private static StatusCode received;

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
        accepted = getMockStatusCode(FriendshipStatus.FR_STATUS_A).asEntity();
        sent = getMockStatusCode(FriendshipStatus.FR_STATUS_S).asEntity();
        received = getMockStatusCode(FriendshipStatus.FR_STATUS_R).asEntity();
    }

    @Test
    void getPostList_NoPosts_EmptyList() {
        given(postRepository.findAllByPersonIdOrderByCreatedAtDesc(person1.getId())).willReturn(List.of());

        List<PostDto> result = service.getPostList(person1.getId());

        assertEquals(List.of(), result);
    }

    @Test
    void getPostList_HasPosts_ListOfPosts() {
        List<Post> postList = List.of(post3, post4);
        given(postRepository.findAllByPersonIdOrderByCreatedAtDesc(person2.getId())).willReturn(postList);

        List<PostDto> result = service.getPostList(person1.getId());

        then(postMapper).should().toDtoList(postList);
        assertEquals(List.of(getPostDto(post3), getPostDto(post4)), result);
    }

    @Test
    void getFeed_NoFriends_EmptyList() {
        int page = 0;
        int limit = 5;
        Pageable query = PageRequest.of(page, limit, Sort.by("createdAt").descending());
        given(friendshipRepository.findFriendshipsByPersonIdAndStatusCode(person1.getId(), accepted.getCode()))
                .willReturn(List.of());
        given(postRepository.findAllByPersonIdIn(List.of(), query)).willReturn(List.of());

        List<PostDto> result = service.getFeed(person1.getId(), page, limit);

        assertEquals(List.of(), result);
    }

    @Test
    void getFeed_HasFriends_ListOfFriendsPosts() {
        int page = 0;
        int limit = 5;
        Pageable query = PageRequest.of(page, limit, Sort.by("createdAt").descending());
        Friendship friendship1 = Friendship.builder().person(person1).friend(person2).status(accepted).id(1L).build();
        Friendship friendship2 = Friendship.builder().person(person1).friend(person3).status(accepted).id(2L).build();
        List<Post> postList = List.of(post6, post5, post4, post3);
        given(friendshipRepository.findFriendshipsByPersonIdAndStatusCode(person1.getId(), accepted.getCode()))
                .willReturn(List.of(friendship1, friendship2));
        given(postRepository.findAllByPersonIdIn(List.of(person2.getId(), person3.getId()), query)).willReturn(postList);

        List<PostDto> result = service.getFeed(person1.getId(), page, limit);

        List<PostDto> correctResult = List.of(getPostDto(post6), getPostDto(post5), getPostDto(post4), getPostDto(post3));
        then(postMapper).should().toDtoList(postList);
        assertEquals(correctResult, result);
    }

    @Test
    void addPost() {

    }

    @Test
    void deletePost() {

    }

}