package ee.iti0302.veebiback.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;

@ControllerAdvice
public final class GlobalExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ExceptionResponse handleRuntimeException(ServletWebRequest req, RuntimeException ex) {
        ex.printStackTrace();
        return new ExceptionResponse(req.getRequest().getRequestURI(), ex.getMessage());
    }

    @ExceptionHandler({EmailInUseException.class, IncorrectCredentialsException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody ExceptionResponse handleAuthException(ServletWebRequest req, RuntimeException ex) {
        return new ExceptionResponse(req.getRequest().getRequestURI(), ex.getMessage());
    }

    /* todo:
    *   - For client caused exceptions: HttpStatus.BAD_REQUEST
    *   - Need to catch Hibernate validator exceptions.
    */
}
