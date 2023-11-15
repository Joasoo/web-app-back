package ee.iti0302.veebiback.service.mapper;

import ee.iti0302.veebiback.domain.Person;
import ee.iti0302.veebiback.dto.EditProfileDataDto;
import ee.iti0302.veebiback.dto.ViewProfileDataDto;
import ee.iti0302.veebiback.dto.RegisterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {StatusCodeMapper.class})
public interface PersonMapper {
    EditProfileDataDto toEditProfileDataDto(Person entity);
    Person registerDtoToEntity(RegisterDto dto);

    /* If updating a Person entity that is still attached to the persistence context,
    * you need to use the toEntity method to create a new StatusCode instance, since
    * updating the attached StatusCode instance directly will result in an exception being thrown. */
    @Mapping(target = "relationshipStatus", ignore = true)
    void updateProfileDataFromDto(EditProfileDataDto dto, @MappingTarget Person entity);
    Person fromEditDtoToPerson(EditProfileDataDto dto);
    ViewProfileDataDto entityToProfileDataDto(Person entity);
}
