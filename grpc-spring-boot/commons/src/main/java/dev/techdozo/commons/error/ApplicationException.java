package dev.techdozo.commons.error;

import java.util.Map;

public class ApplicationException extends BaseException {

  public ApplicationException() {
    super();
  }

  public ApplicationException(Map<String, String> errorMetaData) {
    super(errorMetaData);
  }

  public ApplicationException(String message) {
    super(message);
  }

  public ApplicationException(String message, Map<String, String> errorMetaData) {
    super(message, errorMetaData);
  }

  public ApplicationException(String message, Throwable cause) {
    super(message, cause);
  }

  public ApplicationException(String message, Map<String, String> errorMetaData, Throwable cause) {
    super(message, errorMetaData, cause);
  }

  public ApplicationException(Throwable cause) {
    super(cause);
  }

  public ApplicationException(Map<String, String> errorMetaData, Throwable cause) {
    super(errorMetaData, cause);
  }
}
