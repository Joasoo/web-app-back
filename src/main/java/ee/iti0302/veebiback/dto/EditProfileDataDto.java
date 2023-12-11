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

import static ee.iti0302.veebiback.util.constant.ExceptionMessageConstant.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditProfileDataDto {
    @NotNull
    private Long id;
    @NotEmpty(message = FIRST_NAME_NOT_EMPTY)
    private String firstName;
    @NotEmpty(message = LAST_NAME_NOT_EMPTY)
    private String lastName;
    @NotNull(message = DATE_OF_BIRTH_NOT_NULL)
    private LocalDate dateOfBirth;
    @Email(message = EMAIL_VALID_FORMAT)
    @NotEmpty(message = EMAIL_NOT_EMPTY)
    private String email;
    private StatusCodeDto relationshipStatus;
    private String residence;
    private String hometown;
    private String workplace;
    @Size(max = 500, message = BIO_MAX_SIZE)
    private String bio;
}
