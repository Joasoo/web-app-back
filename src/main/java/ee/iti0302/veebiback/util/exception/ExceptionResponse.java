package ee.iti0302.veebiback.util.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {
    private String path;
    private String cause;

    public ExceptionResponse(String path, String cause) {
        this.path = path;
        this.cause = cause;
    }
}
