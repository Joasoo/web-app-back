package ee.iti0302.veebiback.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ViewProfileDataDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private StatusCodeDto relationshipStatus;
    private String workplace;
    private String residence;
    private String hometown;
    private String bio;
}
