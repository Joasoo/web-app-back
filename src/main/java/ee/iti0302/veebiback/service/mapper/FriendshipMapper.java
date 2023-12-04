package ee.iti0302.veebiback.service.mapper;

import ee.iti0302.veebiback.domain.Friendship;
import ee.iti0302.veebiback.dto.FriendListDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FriendshipMapper {
    @Mapping(source = "friend.id", target = "id")
    @Mapping(source = "friend.firstName", target = "name.firstName")
    @Mapping(source = "friend.lastName", target = "name.lastName")
    FriendListDto toFriendListDto(Friendship friendship);

    List<FriendListDto> toFriendListDtos(List<Friendship> friendships);
}
