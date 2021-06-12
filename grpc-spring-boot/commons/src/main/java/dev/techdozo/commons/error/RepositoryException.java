package dev.techdozo.commons.error;

import java.util.Map;

public class RepositoryException extends BaseException {

  public RepositoryException(ErrorCode errorCode,String message, Map<String, String> errorMetaData) {
    super(errorCode,message, errorMetaData);
  }

  public RepositoryException(ErrorCode errorCode,String message, Map<String, String> errorMetaData, Throwable cause) {
    super(errorCode,message, errorMetaData, cause);
  }
}
