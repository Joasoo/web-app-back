package ee.iti0302.veebiback.service;

import ee.iti0302.veebiback.domain.Friendship;
import ee.iti0302.veebiback.domain.Person;
import ee.iti0302.veebiback.dto.BaseDto;
import ee.iti0302.veebiback.dto.FriendshipDto;
import ee.iti0302.veebiback.repository.FriendshipRepository;
import ee.iti0302.veebiback.repository.PersonRepository;
import ee.iti0302.veebiback.service.mapper.FriendshipMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendshipService {
    private final PersonRepository personRepository;
    private final FriendshipRepository friendshipRepository;
    private final FriendshipMapper friendshipMapper;

    public FriendshipDto getFriendshipStatus(FriendshipDto dto) {
        Long personId = dto.getPerson().getId();
        Long friendId = dto.getFriend().getId();
        Optional<Friendship> personToFriendRequest =
                friendshipRepository.findFriendshipByPerson_IdAndFriend_Id(personId, friendId);
        Optional<Friendship> friendToPersonRequest =
                friendshipRepository.findFriendshipByPerson_IdAndFriend_Id(friendId, personId);
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

    public BaseDto makeFriendRequest(FriendshipDto request) {
        Optional<Person> optionalPerson = personRepository.findById(request.getPerson().getId());
        Optional<Person> optionalFriend = personRepository.findById(request.getPerson().getId());


        if (optionalPerson.isPresent() && optionalFriend.isPresent()) {
            Friendship friendship = new Friendship();
            friendship.setPerson(optionalPerson.get());
            friendship.setFriend(optionalFriend.get());

            Long personId = optionalPerson.get().getId();
            Long friendId = optionalFriend.get().getId();

            Optional<Friendship> optionalExistingRequest =
                    friendshipRepository.findFriendshipByPerson_IdAndFriend_Id(friendId, personId);

            // Check if friend has also made a request to you just in case (same time?)
            if (optionalExistingRequest.isPresent()) {
                Friendship existingRequest = optionalExistingRequest.get();
                existingRequest.setConfirmed(true);
                friendshipRepository.save(existingRequest);
                friendship.setConfirmed(true);
            } else {
                friendship.setConfirmed(false);
            }

            friendshipRepository.save(friendship);
        }
        return new BaseDto();
    }

    public BaseDto acceptFriendRequest(FriendshipDto request) {
        Long personId = request.getPerson().getId();
        Long friendId = request.getFriend().getId();
        Optional<Friendship> requestFromPerson =
                friendshipRepository.findFriendshipByPerson_IdAndFriend_Id(personId, friendId);
        Optional<Friendship> requestFromFriend =
                friendshipRepository.findFriendshipByPerson_IdAndFriend_Id(friendId, personId);

        // Check it both ways, so it doesn't matter which way person and friend are sent in DTO.
        if (requestFromPerson.isPresent()) {
            Friendship friendship = requestFromPerson.get();
            friendship.setConfirmed(true);

            // Create a new friendship for the other person (who accepted the request)
            Friendship newFriendship = createFriendship(friendship.getFriend(), friendship.getPerson());

            friendshipRepository.save(friendship);
            friendshipRepository.save(newFriendship);

        } else if (requestFromFriend.isPresent()) {
            Friendship friendship = requestFromFriend.get();
            friendship.setConfirmed(true);

            // Create a new friendship for the other person (who accepted the request)
            Friendship newFriendship = createFriendship(friendship.getPerson(), friendship.getFriend());

            friendshipRepository.save(friendship);
            friendshipRepository.save(newFriendship);
        }

        return new BaseDto();
    }

    public BaseDto cancelFriendRequest(FriendshipDto request) {
        Optional<Person> optionalPerson = personRepository.findById(request.getPerson().getId());
        Optional<Person> optionalFriend = personRepository.findById(request.getFriend().getId());

        if (optionalPerson.isPresent() && optionalFriend.isPresent()) {
            Long personId = optionalPerson.get().getId();
            Long friendId = optionalFriend.get().getId();

            // Check it both ways (cancel own request or decline other request)
            Optional<Friendship> personRequest =
                    friendshipRepository.findFriendshipByPerson_IdAndFriend_Id(personId, friendId);
            Optional<Friendship> friendRequest =
                    friendshipRepository.findFriendshipByPerson_IdAndFriend_Id(friendId, personId);

            // Delete the request
            personRequest.ifPresent(friendshipRepository::delete);
            friendRequest.ifPresent(friendshipRepository::delete);
        }

        return new BaseDto();
    }


    private Friendship createFriendship(Person person, Person friend) {
        Friendship friendship = new Friendship();
        friendship.setPerson(person);
        friendship.setFriend(friend);
        return friendship;
    }
}
