package ee.iti0302.veebiback.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditProfileDataDto {
    private Long id;
    @NotEmpty(message = "First name cannot be empty.")
    private String firstName;
    @NotEmpty(message = "Last name cannot be empty.")
    private String lastName;
    @NotNull(message = "Must provide a date of birth.")
    private LocalDate dateOfBirth;
    @Email(message = "Must be a correct e-mail.")
    @NotEmpty(message = "E-mail cannot be empty.")
    private String email;
    private StatusCodeDto relationshipStatus;
    private String residence;
    private String hometown;
    private String workplace;
    @Size(max = 500, message = "Bio max size is 500 characters.")
    private String bio;
}
