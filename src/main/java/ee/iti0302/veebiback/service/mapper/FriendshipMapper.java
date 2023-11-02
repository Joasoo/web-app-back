package ee.iti0302.veebiback.service.mapper;

import ee.iti0302.veebiback.domain.Friendship;
import ee.iti0302.veebiback.dto.FriendshipDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FriendshipMapper {
    @Mapping(source = "person.id", target = "person.id")
    @Mapping(source = "person.firstName", target = "person.firstName")
    @Mapping(source = "person.lastName", target = "person.lastName")
    @Mapping(source = "friend.id", target = "friend.id")
    @Mapping(source = "friend.firstName", target = "friend.firstName")
    @Mapping(source = "friend.lastName", target = "friend.lastName")
    FriendshipDto toDto(Friendship friendship);
}
