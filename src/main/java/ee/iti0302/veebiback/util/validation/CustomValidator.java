package ee.iti0302.veebiback.util.validation;
;

public interface CustomValidator {

    <T> void validateWithThrow(T object, Class<?>... groups);
}
