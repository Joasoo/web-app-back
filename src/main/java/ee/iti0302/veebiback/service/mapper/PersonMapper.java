package ee.iti0302.veebiback.service.mapper;

import ee.iti0302.veebiback.domain.Person;
import ee.iti0302.veebiback.dto.ProfileDataDto;
import ee.iti0302.veebiback.dto.RegisterDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PersonMapper {
    Person registerDtoToEntity(RegisterDto dto);
    ProfileDataDto entityToProfileDataDto(Person entity);
}
