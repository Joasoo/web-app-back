package ee.iti0302.veebiback.mapper;

import ee.iti0302.veebiback.domain.Post;
import ee.iti0302.veebiback.dto.PostDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
    @Mapping(source = "person.id", target = "author.id")
    @Mapping(source = "person.firstName", target = "author.firstName")
    @Mapping(source = "person.lastName", target = "author.lastName")
    PostDto toDto(Post entity);
    List<PostDto> toDtoList(List<Post> entityList);

    @InheritInverseConfiguration
    Post toEntity(PostDto dto);
}
