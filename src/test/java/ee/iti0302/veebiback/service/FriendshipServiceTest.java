package ee.iti0302.veebiback.service;

import ee.iti0302.veebiback.domain.Friendship;
import ee.iti0302.veebiback.domain.Person;
import ee.iti0302.veebiback.domain.StatusCode;
import ee.iti0302.veebiback.dto.FriendListDto;
import ee.iti0302.veebiback.repository.FriendshipRepository;
import ee.iti0302.veebiback.repository.PersonRepository;
import ee.iti0302.veebiback.repository.StatusCodeRepository;
import ee.iti0302.veebiback.service.mapper.FriendshipMapper;
import ee.iti0302.veebiback.service.mapper.FriendshipMapperImpl;
import ee.iti0302.veebiback.service.mapper.StatusCodeMapper;
import ee.iti0302.veebiback.service.mapper.StatusCodeMapperImpl;
import ee.iti0302.veebiback.util.constant.FriendshipStatus;
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
class FriendshipServiceTest {
    @Mock
    private FriendshipRepository friendshipRepository;
    @Mock
    private PersonRepository personRepository;
    @Mock
    private StatusCodeRepository statusCodeRepository;
    @Spy
    private FriendshipMapper friendshipMapper = new FriendshipMapperImpl();
    @Spy
    private StatusCodeMapper statusCodeMapper = new StatusCodeMapperImpl();
    @InjectMocks
    private FriendshipService service;

    private static Person person1;
    private static Person person2;
    private static Person person3;
    private static Person person4;
    private static StatusCode accepted;
    private static StatusCode sent;
    private static StatusCode received;

    @BeforeAll
    static void setUp() {
        person1 = getMockPerson();
        person2 = getMockPerson(person1.getId());
        person3 = getMockPerson(List.of(person1.getId(), person2.getId()));
        person4 = getMockPerson(List.of(person1.getId(), person2.getId(), person3.getId()));
        accepted = getMockStatusCode(FriendshipStatus.FR_STATUS_A).asEntity();
        sent = getMockStatusCode(FriendshipStatus.FR_STATUS_S).asEntity();
        received = getMockStatusCode(FriendshipStatus.FR_STATUS_R).asEntity();
    }

    @Test
    void getFriendshipStatus_NoRelation_EmptyDto() {
        // Given
        given(friendshipRepository.findFriendshipByPersonIdAndFriendId(1L, 2L)).willReturn(Optional.empty());

        // When
        FriendListDto result = service.getFriendshipStatus(1L, 2L);

        // Then
        then(friendshipRepository).should().findFriendshipByPersonIdAndFriendId(1L, 2L);
        assertEquals(new FriendListDto(), result);
    }

    @Test
    void getFriendshipStatus_AlreadyFriends_FilledDtoWithStatusCodeAccepted() {
        Friendship friendship = Friendship.builder().status(accepted).person(person1).friend(person2).id(1L).build();
        FriendListDto friendListDto = getFriendListDto(person2, accepted);
        given(friendshipRepository.findFriendshipByPersonIdAndFriendId(person1.getId(), person2.getId()))
                .willReturn(Optional.of(friendship));

        FriendListDto result = service.getFriendshipStatus(person1.getId(), person2.getId());

        then(friendshipMapper).should().toFriendListDto(friendship);
        then(friendshipRepository).should().findFriendshipByPersonIdAndFriendId(person1.getId(), person2.getId());
        assertEquals(friendListDto, result);
    }

    @Test
    void getFriendshipStatus_SentFriendRequest_FilledDtoWithStatusCodeSent() {
        Friendship friendship = Friendship.builder().status(sent).person(person1).friend(person2).id(2L).build();
        FriendListDto friendListDto = getFriendListDto(person2, sent);
        given(friendshipRepository.findFriendshipByPersonIdAndFriendId(person1.getId(), person2.getId()))
                .willReturn(Optional.of(friendship));

        FriendListDto result = service.getFriendshipStatus(person1.getId(), person2.getId());

        then(friendshipMapper).should().toFriendListDto(friendship);
        then(friendshipRepository).should().findFriendshipByPersonIdAndFriendId(person1.getId(), person2.getId());
        assertEquals(friendListDto, result);
    }

    @Test
    void getFriendshipStatus_ReceivedFriendRequest_FilledDtoWithStatusCodeReceived() {
        Friendship friendship = Friendship.builder().status(received).person(person3).friend(person4).id(3L).build();
        FriendListDto friendListDto = getFriendListDto(person4, received);
        given(friendshipRepository.findFriendshipByPersonIdAndFriendId(person3.getId(), person4.getId()))
                .willReturn(Optional.of(friendship));

        FriendListDto result = service.getFriendshipStatus(person3.getId(), person4.getId());

        then(friendshipMapper).should().toFriendListDto(friendship);
        then(friendshipRepository).should().findFriendshipByPersonIdAndFriendId(person3.getId(), person4.getId());
        assertEquals(friendListDto, result);
    }

    @Test
    void getFriendsList_NoFriends_EmptyList() {
        given(friendshipRepository.findFriendshipsByPersonId(person1.getId())).willReturn(List.of());

        List<FriendListDto> result = service.getFriendsList(person1.getId());

        then(friendshipRepository).should().findFriendshipsByPersonId(person1.getId());
        assertEquals(List.of(), result);
    }

    @Test
    void getFriendsList_ManyFriends_ListOfFriends() {
        Friendship friendship1 = Friendship.builder().person(person2).friend(person1).status(accepted).build();
        Friendship friendship2 = Friendship.builder().person(person2).friend(person3).status(accepted).build();
        Friendship friendship3 = Friendship.builder().person(person2).friend(person4).status(accepted).build();
        List<Friendship> queryResult = List.of(friendship1, friendship2, friendship3);
        given(friendshipRepository.findFriendshipsByPersonId(person2.getId())).willReturn(queryResult);

        List<FriendListDto> result = service.getFriendsList(person2.getId());

        FriendListDto dto1 = getFriendListDto(person1, accepted);
        FriendListDto dto2 = getFriendListDto(person3, accepted);
        FriendListDto dto3 = getFriendListDto(person4, accepted);
        List<FriendListDto> correctResult = List.of(dto1, dto2, dto3);
        then(statusCodeMapper).should(times(3)).toDto(accepted);
        assertEquals(correctResult, result);
    }

    @Test
    void getFriendsList_FriendsAndSentRequests_ListOfFriendsAndSentRequests() {
        Friendship friendship1 = Friendship.builder().person(person2).friend(person1).status(sent).build();
        Friendship friendship2 = Friendship.builder().person(person2).friend(person3).status(sent).build();
        Friendship friendship3 = Friendship.builder().person(person2).friend(person4).status(accepted).build();
        List<Friendship> queryResult = List.of(friendship1, friendship2, friendship3);
        given(friendshipRepository.findFriendshipsByPersonId(person2.getId())).willReturn(queryResult);

        List<FriendListDto> result = service.getFriendsList(person2.getId());

        FriendListDto dto1 = getFriendListDto(person1, sent);
        FriendListDto dto2 = getFriendListDto(person3, sent);
        FriendListDto dto3 = getFriendListDto(person4, accepted);
        List<FriendListDto> correctResult = List.of(dto1, dto2, dto3);
        then(statusCodeMapper).should(times(2)).toDto(sent);
        then(statusCodeMapper).should().toDto(accepted);
        assertEquals(correctResult, result);
    }

    @Test
    void getFriendsList_OnlyReceivedRequests_EmptyList() {
        Friendship friendship1 = Friendship.builder().person(person2).friend(person1).status(received).build();
        Friendship friendship2 = Friendship.builder().person(person2).friend(person3).status(received).build();
        List<Friendship> queryResult = List.of(friendship1, friendship2);
        given(friendshipRepository.findFriendshipsByPersonId(person2.getId())).willReturn(queryResult);

        List<FriendListDto> result = service.getFriendsList(person2.getId());

        assertEquals(List.of(), result);
    }

    @Test
    void getReceivedRequests_NoRequests_EmptyList() {
        given(friendshipRepository.findFriendshipsByPersonIdAndStatusCode(person1.getId(), received.getCode()))
                .willReturn(List.of());

        List<FriendListDto> result = service.getReceivedRequests(person1.getId());

        assertEquals(List.of(), result);
    }

    @Test
    void getReceivedRequests_ManyRequests_ListOfRequests() {
        Friendship friendship1 = Friendship.builder().person(person2).friend(person1).status(received).build();
        Friendship friendship2 = Friendship.builder().person(person2).friend(person3).status(received).build();
        List<Friendship> queryResult = List.of(friendship1, friendship2);
        given(friendshipRepository.findFriendshipsByPersonIdAndStatusCode(person2.getId(), received.getCode()))
                .willReturn(queryResult);

        List<FriendListDto> result = service.getReceivedRequests(person2.getId());

        FriendListDto dto1 = getFriendListDto(person1, received);
        FriendListDto dto2 = getFriendListDto(person3, received);
        List<FriendListDto> correctResult = List.of(dto1, dto2);
        then(friendshipMapper).should().toFriendListDtos(List.of(friendship1, friendship2));
        assertEquals(correctResult, result);
    }

    @Test
    void addFriend() {

    }

    @Test
    void removeFriend() {
    }
}