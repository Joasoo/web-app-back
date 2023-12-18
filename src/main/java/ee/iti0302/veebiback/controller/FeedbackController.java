package ee.iti0302.veebiback.controller;

import ee.iti0302.veebiback.dto.BaseDto;
import ee.iti0302.veebiback.dto.FeedbackDto;
import ee.iti0302.veebiback.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PostMapping
    public BaseDto saveFeedback(@RequestBody FeedbackDto dto) {
        return feedbackService.saveFeedback(dto);
    }
/*    @GetMapping
    public List<FeedbackDto> getFeedbacks(@RequestParam Long page, @RequestParam Long limit) {
        return feedbackService.
    }*/
}
