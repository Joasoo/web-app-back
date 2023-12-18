package ee.iti0302.veebiback.service.mapper;

import ee.iti0302.veebiback.domain.Feedback;
import ee.iti0302.veebiback.dto.FeedbackDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FeedbackMapper {
    Feedback toEntity(FeedbackDto dto);
}
