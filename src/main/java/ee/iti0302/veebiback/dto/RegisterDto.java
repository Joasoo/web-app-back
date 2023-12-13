package ee.iti0302.veebiback.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

import static ee.iti0302.veebiback.util.constant.ExceptionMessageConstant.*;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    @NotEmpty(message = FIRST_NAME_NOT_EMPTY)
    private String firstName;

    @NotEmpty(message = LAST_NAME_NOT_EMPTY)
    private String lastName;

    @NotEmpty(message = EMAIL_NOT_EMPTY)
    @Email(message = EMAIL_VALID_FORMAT)
    private String email;

    @NotNull(message = DATE_OF_BIRTH_NOT_NULL)
    private LocalDate dateOfBirth;

    @NotEmpty(message = PASSWORD_NOT_EMPTY)
    private String password;

    private StatusCodeDto relationshipStatus;

    private String workplace;

    private String residence;

    private String hometown;
}
