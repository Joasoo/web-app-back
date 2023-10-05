package ee.iti0302.veebiback.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;


@Getter
@Setter
@ToString
public class RegisterDto {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private String password;
    // private Object relationshipStatus; /*todo*/
    private String workplace;
    private String residence;
    private String hometown;
}
