package ee.iti0302.veebiback.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddPostDto {
    private Long id;
    private String content;
}
