package ee.iti0302.veebiback.util.validation;

import ee.iti0302.veebiback.util.exception.ApplicationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomValidatorImpl implements CustomValidator {
    private final Validator validator;

    @Override
    public <T> void validateWithThrow(T object, Class<?>... groups) {
        var constraints = validator.validate(object, groups);
        if (constraints.iterator().hasNext()) {
            throw new ApplicationException(constraints.iterator().next().getMessage());
        }
    }
}
