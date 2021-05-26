package dev.techdozo.commons.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    @ExceptionHandler(RepositoryException.class)
    public ResponseEntity<Object> handleException(RepositoryException ex) {
        ErrorResponse errorResponse =
                this.getErrorResponse((ex.getCause() == null) ? ex : ex.getCause());
        return this.buildResponseEntity(errorResponse);
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse) {
        return null;
    }

    private ErrorResponse getErrorResponse(Throwable throwable) {
        return null;
    }
}
