package ee.iti0302.veebiback.repository;

import ee.iti0302.veebiback.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    boolean existsByEmailIgnoreCase(String email);
}
