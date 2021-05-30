package dev.techdozo.commons.error;

import java.util.Map;

public class ServiceException extends BaseException {

  public ServiceException() {
    super();
  }

  public ServiceException(Map<String, String> errorMetaData) {
    super(errorMetaData);
  }

  public ServiceException(String message) {
    super(message);
  }

  public ServiceException(String message, Map<String, String> errorMetaData) {
    super(message, errorMetaData);
  }

  public ServiceException(String message, Throwable cause) {
    super(message, cause);
  }

  public ServiceException(String message, Map<String, String> errorMetaData, Throwable cause) {
    super(message, errorMetaData, cause);
  }

  public ServiceException(Throwable cause) {
    super(cause);
  }

  public ServiceException(Map<String, String> errorMetaData, Throwable cause) {
    super(errorMetaData, cause);
  }
}
