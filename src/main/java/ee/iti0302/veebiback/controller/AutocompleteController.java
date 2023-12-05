package ee.iti0302.veebiback.controller;

import ee.iti0302.veebiback.dto.PersonFullNameDto;
import ee.iti0302.veebiback.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/autocomplete")
public class AutocompleteController {
    private final SearchService searchService;

    @GetMapping("search")
    public List<PersonFullNameDto> searchForPeople(@RequestParam String query) {
        return searchService.searchForPeople(query);
    }
}
