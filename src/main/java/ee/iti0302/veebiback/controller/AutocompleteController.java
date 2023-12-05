package ee.iti0302.veebiback.controller;

import ee.iti0302.veebiback.dto.PersonFullNameDto;
import ee.iti0302.veebiback.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/autocomplete")
public class AutocompleteController {
    private final SearchService searchService;

    @GetMapping("search/{query}")
    public List<PersonFullNameDto> searchForPeople(@PathVariable String query) {
        return searchService.searchForPeople(query);
    }
}
