package ee.iti0302.veebiback.repository;

import ee.iti0302.veebiback.domain.StatusCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusCodeRepository extends JpaRepository<StatusCode, String> {
    List<StatusCode> findAllByCodeClass(String codeClass);
    Optional<StatusCode> findByCode(String code);
}
