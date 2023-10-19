package ee.iti0302.veebiback.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {
    private Long id;
    private String content;
    private PersonFullNameDto author;
    private LocalDateTime createdAt;
}
