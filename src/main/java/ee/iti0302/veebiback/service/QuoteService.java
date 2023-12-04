package ee.iti0302.veebiback.service;

import ee.iti0302.veebiback.domain.Quote;
import ee.iti0302.veebiback.dto.QuoteDto;
import ee.iti0302.veebiback.repository.QuoteRepository;
import ee.iti0302.veebiback.service.mapper.QuoteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class QuoteService {
    private final QuoteRepository repository;
    private final QuoteMapper mapper;

    public QuoteDto randomQuote() {
        List<Quote> quotes = repository.findAll();
        Quote random = quotes.get(new Random().nextInt(quotes.size()));
        return mapper.toDto(random);
    }
}
