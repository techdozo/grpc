package dev.techdozo.commons.error;

import java.util.Map;

public class RecordNotFoundException extends RepositoryException {

  public RecordNotFoundException() {
    super();
  }

  public RecordNotFoundException(Map<String, String> errorMetaData) {
    super(errorMetaData);
  }

  public RecordNotFoundException(String message) {
    super(message);
  }

  public RecordNotFoundException(String message, Map<String, String> errorMetaData) {
    super(message, errorMetaData);
  }

  public RecordNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public RecordNotFoundException(String message, Map<String, String> errorMetaData, Throwable cause) {
    super(message, errorMetaData, cause);
  }

  public RecordNotFoundException(Throwable cause) {
    super(cause);
  }

  public RecordNotFoundException(Map<String, String> errorMetaData, Throwable cause) {
    super(errorMetaData, cause);
  }
}
