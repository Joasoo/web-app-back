package ee.iti0302.veebiback.service;

import ee.iti0302.veebiback.domain.Friendship;
import ee.iti0302.veebiback.domain.Person;
import ee.iti0302.veebiback.domain.StatusCode;
import ee.iti0302.veebiback.dto.FriendListDto;
import ee.iti0302.veebiback.dto.FullNameDto;
import ee.iti0302.veebiback.dto.StatusCodeDto;
import ee.iti0302.veebiback.repository.FriendshipRepository;
import ee.iti0302.veebiback.repository.PersonRepository;
import ee.iti0302.veebiback.repository.StatusCodeRepository;
import ee.iti0302.veebiback.service.mapper.FriendshipMapper;
import ee.iti0302.veebiback.service.mapper.StatusCodeMapper;
import ee.iti0302.veebiback.util.enums.FriendshipStatus;
import ee.iti0302.veebiback.util.enums.StatusCodeClass;
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
    private FriendshipMapper friendshipMapper;
    @Spy
    private StatusCodeMapper statusCodeMapper;
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
        person1 = getRandomPerson();
        person2 = getRandomPerson(person1.getId());
        person3 = getRandomPerson(List.of(person1.getId(), person2.getId()));
        person4 = getRandomPerson(List.of(person1.getId(), person2.getId(), person3.getId()));
        accepted = StatusCode.builder()
                .value("Accepted")
                .code(FriendshipStatus.FR_STATUS_A.name())
                .codeClass(StatusCodeClass.FR_STATUS.name()).build();
        sent = StatusCode.builder()
                .value("Sent")
                .code(FriendshipStatus.FR_STATUS_S.name())
                .codeClass(StatusCodeClass.FR_STATUS.name()).build();
        received = StatusCode.builder()
                .value("Received")
                .code(FriendshipStatus.FR_STATUS_R.name())
                .codeClass(StatusCodeClass.FR_STATUS.name()).build();
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
        FullNameDto fullNameDto = getFullNameDto(person2);
        StatusCodeDto statusCodeDto = getStatusCodeDto(accepted);
        FriendListDto friendListDto = FriendListDto.builder().id(person2.getId()).name(fullNameDto).status(statusCodeDto)
                .build();
        given(friendshipRepository.findFriendshipByPersonIdAndFriendId(person1.getId(), person2.getId()))
                .willReturn(Optional.of(friendship));

        verify(friendshipRepository).findFriendshipByPersonIdAndFriendId(person1.getId(), person2.getId());
        FriendListDto result = service.getFriendshipStatus(person1.getId(), person2.getId());

        then(friendshipMapper).should().toFriendListDto(friendship);
        then(friendshipRepository).should().findFriendshipByPersonIdAndFriendId(person1.getId(), person2.getId());
        assertEquals(friendListDto, result);
    }

    @Test
    void getFriendshipStatus_SentFriendRequest_FilledDtoWithStatusCodeSent() {
        Friendship friendship = Friendship.builder().status(sent).person(person1).friend(person2).id(2L).build();
        FullNameDto fullNameDto = getFullNameDto(person2);
        StatusCodeDto statusCodeDto = getStatusCodeDto(sent);
        FriendListDto friendListDto = FriendListDto.builder().id(person2.getId()).name(fullNameDto).status(statusCodeDto)
                .build();
        given(friendshipRepository.findFriendshipByPersonIdAndFriendId(person1.getId(), person2.getId()))
                .willReturn(Optional.of(friendship));

        FriendListDto result = service.getFriendshipStatus(person1.getId(), person2.getId());

        then(friendshipMapper).should().toFriendListDto(friendship);
        then(friendshipRepository).should().findFriendshipByPersonIdAndFriendId(person1.getId(), person2.getId());
        assertEquals(friendListDto, result);
    }

    @Test
    void getFriendshipStatus_ReceivedFriendRequest_FilledDtoWithStatusCodeReceived() {
        Friendship friendship = Friendship.builder().status(sent).person(person3).friend(person4).id(3L).build();
        FullNameDto fullNameDto = getFullNameDto(person4);
        StatusCodeDto statusCodeDto = getStatusCodeDto(received);
        FriendListDto friendListDto = FriendListDto.builder().id(person4.getId()).name(fullNameDto).status(statusCodeDto)
                .build();
        given(friendshipRepository.findFriendshipByPersonIdAndFriendId(person3.getId(), person4.getId()))
                .willReturn(Optional.of(friendship));

        FriendListDto result = service.getFriendshipStatus(person3.getId(), person4.getId());

        then(friendshipMapper).should().toFriendListDto(friendship);
        then(friendshipRepository).should().findFriendshipByPersonIdAndFriendId(person3.getId(), person4.getId());
        assertEquals(friendListDto, result);
    }


    @Test
    void addFriend() {
    }

    @Test
    void removeFriend() {
    }

    @Test
    void getFriendsList() {
    }

    @Test
    void getReceivedRequests() {
    }
}