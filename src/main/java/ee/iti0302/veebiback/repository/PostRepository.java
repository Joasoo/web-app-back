package ee.iti0302.veebiback.repository;

import ee.iti0302.veebiback.domain.Post;
import ee.iti0302.veebiback.dto.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByPersonId(Long personId);
}
