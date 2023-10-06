package ee.iti0302.veebiback.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDataDto {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    // private String relationshipStatus;
    private String workplace;
    private String residence;
    private String hometown;
    private String bio;
}
