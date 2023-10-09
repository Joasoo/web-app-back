package ee.iti0302.veebiback.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;

@ControllerAdvice
public final class GlobalExceptionHandler {

    @ExceptionHandler({GenericRestException.class})
    public @ResponseBody RestError handleGenericException(ServletWebRequest req, GenericRestException ex) {
        return new RestError(req.getRequest().getRequestURI(), ex.getTimestamp(), ex.getMessage());
    }


    @ExceptionHandler({EmailInUseException.class, IncorrectCredentialsException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody RestError handleAuthException(ServletWebRequest req, GenericRestException ex) {
        return new RestError(req.getRequest().getRequestURI(), ex.getTimestamp(), ex.getMessage());
    }

}
