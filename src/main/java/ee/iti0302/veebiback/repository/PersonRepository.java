package ee.iti0302.veebiback.repository;

import ee.iti0302.veebiback.domain.Person;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    boolean existsByEmailIgnoreCase(String email);
    Optional<Person> findByEmailIgnoreCase(String email);
    Optional<Person> findByEmailIgnoreCaseAndIdIsNot(@Email @NotNull String email, Long id);

    @Query(value = """
            select *
                from public.person p
                where p.first_name || ' ' || p.last_name ilike '%' || :name || '%'
                limit 10
            """, nativeQuery = true)
    List<Person> searchByFullNameLimit10(String name);
}
