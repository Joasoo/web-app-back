package ee.iti0302.veebiback.service.mapper;

import ee.iti0302.veebiback.domain.Quote;
import ee.iti0302.veebiback.dto.QuoteDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuoteMapper {
    @Mapping(source = "q", target = "quote")
    @Mapping(source = "a", target = "author")
    Quote toEntity(QuoteDto dto);
    @InheritInverseConfiguration
    QuoteDto toDto(Quote entity);
    List<Quote> toEntityList(QuoteDto[] dtoArr);
}
