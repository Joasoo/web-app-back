package ee.iti0302.veebiback.repository;

import ee.iti0302.veebiback.domain.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    Optional<Friendship> findFriendshipByPerson_IdAndFriend_Id(Long fromId, Long toId);
}
