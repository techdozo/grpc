package dev.techdozo.commons.error;

public class RecordNotFoundException extends RepositoryException {

  public RecordNotFoundException() {
    super();
  }

  public RecordNotFoundException(String message) {
    super(message);
  }

  public RecordNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public RecordNotFoundException(Throwable cause) {
    super(cause);
  }
}
