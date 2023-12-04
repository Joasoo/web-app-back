package ee.iti0302.veebiback.repository;

import ee.iti0302.veebiback.domain.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

}
