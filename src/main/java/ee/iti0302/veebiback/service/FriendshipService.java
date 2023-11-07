package ee.iti0302.veebiback.service;

import ee.iti0302.veebiback.domain.Friendship;
import ee.iti0302.veebiback.domain.Person;
import ee.iti0302.veebiback.dto.*;
import ee.iti0302.veebiback.repository.FriendshipRepository;
import ee.iti0302.veebiback.repository.PersonRepository;
import ee.iti0302.veebiback.repository.StatusCodeRepository;
import ee.iti0302.veebiback.service.mapper.FriendshipMapper;
import ee.iti0302.veebiback.service.mapper.StatusCodeMapper;
import ee.iti0302.veebiback.util.enums.FriendshipStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ee.iti0302.veebiback.util.enums.FriendshipStatus.FR_STATUS_A;
import static ee.iti0302.veebiback.util.enums.FriendshipStatus.FR_STATUS_S;

@Service
@RequiredArgsConstructor
public class FriendshipService {
    private final PersonRepository personRepository;
    private final FriendshipRepository friendshipRepository;
    private final FriendshipMapper friendshipMapper;
    private final StatusCodeRepository statusCodeRepository;
    private final StatusCodeMapper statusCodeMapper;

    public FriendshipDto getFriendshipStatus(Long personId, Long friendId) {
        Optional<Friendship> personToFriendRequest =
                friendshipRepository.findFriendshipByPersonIdAndFriendId(personId, friendId);
        Optional<Friendship> friendToPersonRequest =
                friendshipRepository.findFriendshipByPersonIdAndFriendId(friendId, personId);
        // They are already friends
        if (personToFriendRequest.isPresent() && friendToPersonRequest.isPresent()) {
            return friendshipMapper.toDto(personToFriendRequest.get());
        }
        // The person requesting this status has a pending friend request to the other person.
        else if (personToFriendRequest.isPresent()) {
            return friendshipMapper.toDto(personToFriendRequest.get());
        }
        // The other person has a pending friend request to the person requesting this status.
        else if (friendToPersonRequest.isPresent()) {
            return friendshipMapper.toDto(friendToPersonRequest.get());
        }
        // They are not friends and don't have any pending requests.
        return new FriendshipDto();
    }

    public BaseDto addFriend(FriendRequestDto request) {
        Optional<Person> optionalPerson = personRepository.findById(request.getPersonId());
        Optional<Person> optionalFriend = personRepository.findById(request.getFriendId());


        if (optionalPerson.isPresent() && optionalFriend.isPresent()) {
            // Make a new friendship
            Friendship friendship = new Friendship();
            friendship.setPerson(optionalPerson.get());
            friendship.setFriend(optionalFriend.get());

            Long personId = optionalPerson.get().getId();
            Long friendId = optionalFriend.get().getId();

            Optional<Friendship> optionalExistingRequest =
                    friendshipRepository.findFriendshipByPersonIdAndFriendId(friendId, personId);

            // Check if friend has also made a request to you => Make them friends
            if (optionalExistingRequest.isPresent()) {
                Friendship existingRequest = optionalExistingRequest.get();
                var statusCodeOptional = statusCodeRepository.findByCode(FR_STATUS_A.name());
                existingRequest.setStatus(statusCodeOptional.orElseThrow());
                friendshipRepository.save(existingRequest);
                friendship.setStatus(statusCodeOptional.orElseThrow());
            } else {
                var statusCodeOptional = statusCodeRepository.findByCode(FriendshipStatus.FR_STATUS_S.name());
                // Leave it as a request
                friendship.setStatus(statusCodeOptional.orElseThrow());
            }

            friendshipRepository.save(friendship);
        }
        return new BaseDto();
    }

    public BaseDto acceptFriendRequest(FriendRequestDto request) {
        Long personId = request.getPersonId();
        Long friendId = request.getFriendId();
        Optional<Friendship> requestFromPerson =
                friendshipRepository.findFriendshipByPersonIdAndFriendId(personId, friendId);
        Optional<Friendship> requestFromFriend =
                friendshipRepository.findFriendshipByPersonIdAndFriendId(friendId, personId);
        var statusCodeA = statusCodeRepository
                .findByCode(FR_STATUS_A.name())
                .orElseThrow();

        // Check it both ways, so it doesn't matter which way person and friend are sent in DTO.
        if (requestFromPerson.isPresent()) {
            // Confirm the initial request
            Friendship friendship = requestFromPerson.get();
            friendship.setStatus(statusCodeA);

            // Create a new friendship for the other person (who accepted the request)
            Friendship newFriendship = createFriendship(friendship.getFriend(), friendship.getPerson());

            friendshipRepository.save(friendship);
            friendshipRepository.save(newFriendship);

        } else if (requestFromFriend.isPresent()) {
            // Confirm the initial request
            Friendship friendship = requestFromFriend.get();
            friendship.setStatus(statusCodeA);

            // Create a new friendship for the other person (who accepted the request)
            Friendship newFriendship = createFriendship(friendship.getPerson(), friendship.getFriend());

            friendshipRepository.save(friendship);
            friendshipRepository.save(newFriendship);
        }

        return new BaseDto();
    }

    public BaseDto removeFriendship(Long personId, Long friendId) {
        // Check it both ways (cancel own request or decline other request)
        Optional<Friendship> personRequest =
                friendshipRepository.findFriendshipByPersonIdAndFriendId(personId, friendId);
        Optional<Friendship> friendRequest =
                friendshipRepository.findFriendshipByPersonIdAndFriendId(friendId, personId);

        // Delete the request
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

    private Friendship createFriendship(Person person, Person friend) {
        Friendship friendship = new Friendship();
        friendship.setPerson(person);
        friendship.setFriend(friend);
        return friendship;
    }
}
