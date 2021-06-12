package dev.techdozo.commons.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ServiceException.class)
  public ResponseEntity<ErrorResponse> handleException(ServiceException cause) {
    var errorResponse = new ErrorResponse();
    var errorCode = cause.getErrorCode();
    errorResponse.setErrorCode(errorCode);
    errorResponse.setMessage(cause.getMessage());
    errorResponse.setDetails(cause.getErrorMetaData());
    return new ResponseEntity<>(errorResponse, errorCode.getHttpStatus());
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleException(ResourceNotFoundException cause) {
    var errorResponse = new ErrorResponse();
    errorResponse.setErrorCode(ErrorCode.RESOURCE_NOT_FOUND);
    errorResponse.setMessage(cause.getMessage());
    errorResponse.setDetails(cause.getErrorMetaData());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }
}
