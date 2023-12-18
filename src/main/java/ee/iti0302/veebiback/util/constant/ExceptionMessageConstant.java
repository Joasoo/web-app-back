package ee.iti0302.veebiback.util.constant;

public final class ExceptionMessageConstant {
    private ExceptionMessageConstant() {}

    public static final String FIRST_NAME_NOT_EMPTY = "First name can not be empty.";
    public static final String LAST_NAME_NOT_EMPTY = "Last name can not be empty.";
    public static final String PASSWORD_NOT_EMPTY = "Password can not be empty.";
    public static final String EMAIL_NOT_EMPTY = "E-mail can not be empty.";
    public static final String DATE_OF_BIRTH_NOT_NULL = "Must provide a valid date of birth.";

    public static final String BIO_MAX_SIZE = "Bio max size is 500 characters.";
    public static final String EMAIL_VALID_FORMAT = "E-mail must be valid.";
    public static final String EMAIL_IN_USE = "This e-mail is already in use";
    public static final String EMAIL_OR_PASSWORD_INCORRECT = "Incorrect e-mail or password.";

    public static final String RATING_NOT_EMPTY = "Rating is required.";
    public static final String RATING_IN_BOUNDS = "Rating must be between 1 and 5.";
}
