package dev.techdozo.commons.error;

import java.util.Map;

public class InvalidOperationException extends ServiceException {

  public InvalidOperationException() {
    super();
  }

  public InvalidOperationException(Map<String, String> errorMetaData) {
    super(errorMetaData);
  }

  public InvalidOperationException(String message) {
    super(message);
  }

  public InvalidOperationException(String message, Map<String, String> errorMetaData) {
    super(message, errorMetaData);
  }

  public InvalidOperationException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidOperationException(String message, Map<String, String> errorMetaData, Throwable cause) {
    super(message, errorMetaData, cause);
  }

  public InvalidOperationException(Throwable cause) {
    super(cause);
  }

  public InvalidOperationException(Map<String, String> errorMetaData, Throwable cause) {
    super(errorMetaData, cause);
  }
}
