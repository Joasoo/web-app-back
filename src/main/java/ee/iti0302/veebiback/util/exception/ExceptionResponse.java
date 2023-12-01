package ee.iti0302.veebiback.util.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionResponse {
    private String path;
    private LocalDateTime timestamp;
    private String cause;
    private Long statusCode;

    public static final String GENERIC_RESPONSE = "An error has occurred. Please try again later.";

    public ExceptionResponse(String path) {
        this(path, null);
    }

    public ExceptionResponse(String path, String cause) {
        this.path = path;
        this.timestamp = LocalDateTime.now();
        this.cause = cause != null ? cause : GENERIC_RESPONSE;
    }
}
