package dev.techdozo.commons.error;

import java.util.Map;

public class RepositoryException extends BaseException {

  public RepositoryException() {
    super();
  }

  public RepositoryException(Map<String, String> errorMetaData) {
    super(errorMetaData);
  }

  public RepositoryException(String message) {
    super(message);
  }

  public RepositoryException(String message, Map<String, String> errorMetaData) {
    super(message, errorMetaData);
  }

  public RepositoryException(String message, Throwable cause) {
    super(message, cause);
  }

  public RepositoryException(String message, Map<String, String> errorMetaData, Throwable cause) {
    super(message, errorMetaData, cause);
  }

  public RepositoryException(Throwable cause) {
    super(cause);
  }

  public RepositoryException(Map<String, String> errorMetaData, Throwable cause) {
    super(errorMetaData, cause);
  }
}
