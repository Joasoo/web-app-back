package ee.iti0302.veebiback.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostDto {
    private Long id;
    private String content;
    private AuthorDto author;
    private LocalDateTime createdAt;

    @Getter
    @Setter
    public static class AuthorDto {
        private Long id;
        private String firstName;
        private String lastName;
    }
}
