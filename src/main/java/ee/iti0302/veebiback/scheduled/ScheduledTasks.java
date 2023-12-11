package ee.iti0302.veebiback.scheduled;

import ee.iti0302.veebiback.domain.Quote;
import ee.iti0302.veebiback.dto.QuoteDto;
import ee.iti0302.veebiback.repository.QuoteRepository;
import ee.iti0302.veebiback.service.mapper.QuoteMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduledTasks {
    private final RestTemplate template;
    private final QuoteRepository repository;
    private final QuoteMapper mapper;

    @Scheduled(fixedDelay = 21_600_000)  // 6h
    public void fetchQuotes() {
        var response = template.getForObject("https://zenquotes.io/api/quotes", QuoteDto[].class);
        if (response != null) {
            List<Quote> previous = repository.findAll();
            List<Quote> fresh = mapper.toEntityList(response);
            repository.saveAll(fresh);
            repository.deleteAllInBatch(previous);
        } else {
            log.info("[ScheduledTasks] Couldn't fetch quotes from API");
        }
    }
}
