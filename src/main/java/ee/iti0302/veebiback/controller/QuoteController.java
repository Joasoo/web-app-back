package ee.iti0302.veebiback.controller;

import ee.iti0302.veebiback.dto.QuoteDto;
import ee.iti0302.veebiback.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/quote")
@RequiredArgsConstructor
public class QuoteController {
    private final QuoteService service;

    @GetMapping
    public QuoteDto getQuote() {
        return service.randomQuote();
    }
}
