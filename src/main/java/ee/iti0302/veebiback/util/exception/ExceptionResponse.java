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

    public ExceptionResponse(String path, String cause) {
        this.path = path;
        this.timestamp = LocalDateTime.now();
        this.cause = cause;
    }
}
