package ee.iti0302.veebiback.repository;

import ee.iti0302.veebiback.domain.Person;
import ee.iti0302.veebiback.domain.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends ListPagingAndSortingRepository<Post, Long> {
    void save(Post post);
    void deleteById(Long id);
    List<Post> findAllByPersonIdOrderByCreatedAtDesc(Long personId);
    // TEST IF OTHER DOESN'T WORK
    // List<Post> findAllByPersonIdInOrderByCreatedAtDesc(List<Long> people, Pageable pageable);
    List<Post> findAllByPersonIdIn(List<Long> people, Pageable pageable);

}
