package ee.iti0302.veebiback.service;

import ee.iti0302.veebiback.domain.Friendship;
import ee.iti0302.veebiback.domain.Person;
import ee.iti0302.veebiback.domain.StatusCode;
import ee.iti0302.veebiback.dto.*;
import ee.iti0302.veebiback.repository.FriendshipRepository;
import ee.iti0302.veebiback.repository.PersonRepository;
import ee.iti0302.veebiback.repository.StatusCodeRepository;
import ee.iti0302.veebiback.service.mapper.FriendshipMapper;
import ee.iti0302.veebiback.service.mapper.StatusCodeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ee.iti0302.veebiback.util.enums.FriendshipStatus.FR_STATUS_A;
import static ee.iti0302.veebiback.util.enums.FriendshipStatus.FR_STATUS_S;
import static ee.iti0302.veebiback.util.enums.FriendshipStatus.FR_STATUS_R;

@Service
@RequiredArgsConstructor
public class FriendshipService {
    private final PersonRepository personRepository;
    private final FriendshipRepository friendshipRepository;
    private final FriendshipMapper friendshipMapper;
    private final StatusCodeRepository statusCodeRepository;
    private final StatusCodeMapper statusCodeMapper;

    public FriendListDto getFriendshipStatus(Long personId, Long friendId) {
        Optional<Friendship> optionalFriendship =
                friendshipRepository.findFriendshipByPersonIdAndFriendId(personId, friendId);
        if (optionalFriendship.isPresent()) {
           Friendship friendship = optionalFriendship.get();
           return friendshipMapper.toFriendListDto(friendship);
        }
        // They are not friends and there aren't any pending requests
        return new FriendListDto();
    }

    public BaseDto addFriend(FriendRequestDto request) {
        Optional<Person> optionalPerson = personRepository.findById(request.getPersonId());
        Optional<Person> optionalFriend = personRepository.findById(request.getFriendId());


        if (optionalPerson.isPresent() && optionalFriend.isPresent()) {
            Long personId = optionalPerson.get().getId();
            Long friendId = optionalFriend.get().getId();

            // Try to find an existing relation between the two people
            Optional<Friendship> optionalRequestPerson =
                    friendshipRepository.findFriendshipByPersonIdAndFriendId(personId, friendId);
            Optional<Friendship> optionalRequestFriend =
                    friendshipRepository.findFriendshipByPersonIdAndFriendId(friendId, personId);

            // If there is an existing relation, accept the friend request
            if (optionalRequestPerson.isPresent() && optionalRequestFriend.isPresent()) {
                Friendship requestPerson = optionalRequestPerson.get();
                Friendship requestFriend = optionalRequestFriend.get();
                acceptRequest(requestPerson, requestFriend);
            }

            // There is no previous relation, make a new friend relation as a request
            else {
                makeRequest(optionalPerson.get(), optionalFriend.get());
            }
        }
        return new BaseDto();
    }

    public BaseDto removeFriend(Long personId, Long friendId) {
        // Delete the (sent) and (received) requests for person and friend
        Optional<Friendship> personRequest =
                friendshipRepository.findFriendshipByPersonIdAndFriendId(personId, friendId);
        Optional<Friendship> friendRequest =
                friendshipRepository.findFriendshipByPersonIdAndFriendId(friendId, personId);

        personRequest.ifPresent(friendshipRepository::delete);
        friendRequest.ifPresent(friendshipRepository::delete);

        return new BaseDto();
    }

    public List<FriendListDto> getFriendsList(Long personId) {
        var friendshipRelations = friendshipRepository.findFriendshipsByPersonId(personId);
        /*
        Valid relations (shown on the front-end) are the ones with FR_STATUS_A or FR_STATUS_S
        since we don't show blocked people in friend's list.
        */
        var validRelations = new ArrayList<FriendListDto>();
        if (friendshipRelations.isEmpty()) {
            return new ArrayList<>();
        }

        for (var relation : friendshipRelations) {
            var status = statusCodeMapper.toDto(relation.getStatus());
            if (List.of(FR_STATUS_A.name(), FR_STATUS_S.name())
                    .contains(status.getCode())) {
                var friend = relation.getFriend();
                var fullName = new FullNameDto(friend.getFirstName(), friend.getLastName());

                var friendListDto = new FriendListDto();
                friendListDto.setId(friend.getId());
                friendListDto.setName(fullName);
                friendListDto.setStatus(status);
                validRelations.add(friendListDto);
            }
        }
        return validRelations;
    }

    // --------------------------------------------- Private methods ---------------------------------------------

    private Friendship createFriendship(Person person, Person friend) {
        Friendship friendship = new Friendship();
        friendship.setPerson(person);
        friendship.setFriend(friend);
        return friendship;
    }

    private void acceptRequest(Friendship requestPerson, Friendship requestFriend) {
        Optional<StatusCode> optionalAccepted = statusCodeRepository.findByCode(FR_STATUS_A.name());
        requestPerson.setStatus(optionalAccepted.orElseThrow());
        requestFriend.setStatus(optionalAccepted.orElseThrow());

        friendshipRepository.save(requestPerson);
        friendshipRepository.save(requestFriend);
    }

    private void makeRequest(Person person, Person friend) {
        Optional<StatusCode> optionalSent = statusCodeRepository.findByCode(FR_STATUS_S.name());
        Optional<StatusCode> optionalReceived = statusCodeRepository.findByCode(FR_STATUS_R.name());

        Friendship newRequestPerson = createFriendship(person, friend);
        Friendship newRequestFriend = createFriendship(friend, person);

        newRequestPerson.setStatus(optionalSent.orElseThrow());
        newRequestFriend.setStatus(optionalReceived.orElseThrow());

        friendshipRepository.save(newRequestPerson);
        friendshipRepository.save(newRequestFriend);
    }
}
