package dev.techdozo.commons.error;

import java.util.Map;

public class ApplicationException extends BaseException {

  public ApplicationException(ErrorCode errorCode,String message, Map<String, String> errorMetaData) {
    super(errorCode,message, errorMetaData);
  }

  public ApplicationException(ErrorCode errorCode,String message, Map<String, String> errorMetaData, Throwable cause) {
    super(errorCode,message, errorMetaData, cause);
  }
}
