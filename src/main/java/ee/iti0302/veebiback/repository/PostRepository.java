package ee.iti0302.veebiback.repository;

import ee.iti0302.veebiback.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByPersonIdOrderByCreatedAtDesc(Long personId);
}
