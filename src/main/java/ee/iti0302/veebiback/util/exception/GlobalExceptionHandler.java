package ee.iti0302.veebiback.util.exception;

import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;

@Slf4j
@ControllerAdvice
public final class GlobalExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ExceptionResponse handleRuntimeException(ServletWebRequest req, RuntimeException ex) {
        log.error(ex.getMessage());
        return new ExceptionResponse(req.getRequest().getRequestURI());
    }

    @ExceptionHandler({AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody ExceptionResponse handleAuthException(ServletWebRequest req, RuntimeException ex) {
        return new ExceptionResponse(req.getRequest().getRequestURI(), ex.getMessage());
    }

    @ExceptionHandler({ApplicationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionResponse handleApplicationException(ServletWebRequest req, RuntimeException ex) {
        return new ExceptionResponse(req.getRequest().getRequestURI(), ex.getMessage());
    }
}
