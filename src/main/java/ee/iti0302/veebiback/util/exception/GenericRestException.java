package ee.iti0302.veebiback.util.exception;


import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Base class for all custom REST exceptions.
 */
@Getter
public abstract class GenericRestException extends RuntimeException {
    private final LocalDateTime timestamp;
    protected GenericRestException(String message) {
        super(message);
        timestamp = LocalDateTime.now();
    }
}
