package ee.iti0302.veebiback.util.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RestError {
    private String path;
    private LocalDateTime timestamp;
    private String cause;

    public RestError(String path, LocalDateTime timestamp, String cause) {
        this.path = path;
        this.timestamp = timestamp;
        this.cause = cause;
    }
}
