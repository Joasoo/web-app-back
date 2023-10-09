package ee.iti0302.veebiback.service.mapper;

import ee.iti0302.veebiback.domain.Person;
import ee.iti0302.veebiback.dto.BaseDto;
import ee.iti0302.veebiback.dto.EditProfileDataDto;
import ee.iti0302.veebiback.dto.RegisterDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PersonMapper {
    EditProfileDataDto toEditProfileDataDto(Person entity);
    Person registerDtoToEntity(RegisterDto dto);
    void updateProfileDataFromDto(EditProfileDataDto dto, @MappingTarget Person entity);
}
