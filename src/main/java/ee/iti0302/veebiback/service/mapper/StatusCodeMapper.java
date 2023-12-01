package ee.iti0302.veebiback.service.mapper;

import ee.iti0302.veebiback.domain.StatusCode;
import ee.iti0302.veebiback.dto.StatusCodeDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StatusCodeMapper {
    StatusCodeDto toDto(StatusCode entity);
    List<StatusCodeDto> toDtoList(List<StatusCode> entityList);
    void updateFromDto(StatusCodeDto dto, @MappingTarget StatusCode entity);
    @Named("toEntity")
    default StatusCode toEntity(StatusCodeDto dto) {
        var entity = new StatusCode();
        updateFromDto(dto, entity);
        return entity;
    }
}
