package ee.iti0302.veebiback.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenDto {
    private Long id;
    private String token;
}
