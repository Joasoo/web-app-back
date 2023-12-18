package ee.iti0302.veebiback.service;

import ee.iti0302.veebiback.dto.BaseDto;
import ee.iti0302.veebiback.dto.FeedbackDto;
import ee.iti0302.veebiback.repository.FeedbackRepository;
import ee.iti0302.veebiback.service.mapper.FeedbackMapper;
import ee.iti0302.veebiback.util.validation.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final CustomValidator validator;
    private final FeedbackMapper feedbackMapper;
    private final FeedbackRepository feedbackRepository;

    public BaseDto saveFeedback(FeedbackDto dto) {
        validator.validateWithThrow(dto);
        System.out.println(dto);
        var entity = feedbackMapper.toEntity(dto);
        feedbackRepository.save(entity);
        return new BaseDto();
    }
}
