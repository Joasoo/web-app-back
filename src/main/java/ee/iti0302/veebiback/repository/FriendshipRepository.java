package ee.iti0302.veebiback.repository;

import ee.iti0302.veebiback.domain.Friendship;
import ee.iti0302.veebiback.domain.StatusCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
    Optional<Friendship> findFriendshipByPersonIdAndFriendId(Long fromId, Long toId);
    List<Friendship> findFriendshipsByPersonId(Long personId);
    List<Friendship> findFriendshipsByPersonIdAndStatusValue(Long personId, String status);
}
