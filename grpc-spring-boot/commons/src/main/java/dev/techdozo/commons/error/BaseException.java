package dev.techdozo.commons.error;

import lombok.Getter;

import java.util.Map;

@Getter
public class BaseException extends RuntimeException {

  private Map<String, String> errorMetaData;

  public BaseException() {
    super();
  }

  public BaseException(Map<String, String> errorMetaData) {
    super();
    this.errorMetaData = errorMetaData;
  }

  public BaseException(String message) {
    super(message);
  }

  public BaseException(String message, Map<String, String> errorMetaData) {
    super(message);
    this.errorMetaData = errorMetaData;
  }

  public BaseException(String message, Throwable cause) {
    super(message, cause);
  }

  public BaseException(String message, Map<String, String> errorMetaData, Throwable cause) {
    super(message, cause);
    this.errorMetaData = errorMetaData;
  }

  public BaseException(Throwable cause) {
    super(cause);
  }

  public BaseException(Map<String, String> errorMetaData, Throwable cause) {
    super(cause);
    this.errorMetaData = errorMetaData;
  }
}
