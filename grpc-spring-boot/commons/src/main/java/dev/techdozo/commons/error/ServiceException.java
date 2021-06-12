package dev.techdozo.commons.error;

import java.util.Map;

public class ServiceException extends BaseException {

  public ServiceException(ErrorCode errorCode, String message, Map<String, String> errorMetaData) {
    super(errorCode, message, errorMetaData);
  }

  public ServiceException(
      ErrorCode errorCode, String message, Map<String, String> errorMetaData, Throwable cause) {
    super(errorCode, message, errorMetaData, cause);
  }
}
