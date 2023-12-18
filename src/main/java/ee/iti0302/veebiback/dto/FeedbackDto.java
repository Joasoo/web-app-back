package ee.iti0302.veebiback.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static ee.iti0302.veebiback.util.constant.ExceptionMessageConstant.RATING_IN_BOUNDS;
import static ee.iti0302.veebiback.util.constant.ExceptionMessageConstant.RATING_NOT_EMPTY;

@Getter
@Setter
@ToString
public class FeedbackDto {

    @NotNull(message = RATING_NOT_EMPTY)
    @Max(value = 5, message = RATING_IN_BOUNDS)
    @Min(value = 1, message = RATING_IN_BOUNDS)
    private Integer rating;
    private String comment;
}
