package dev.techdozo.commons.error;

public class RepositoryException extends BaseException {

  public RepositoryException() {
    super();
  }

  public RepositoryException(String message) {
    super(message);
  }

  public RepositoryException(String message, Throwable cause) {
    super(message, cause);
  }

  public RepositoryException(Throwable cause) {
    super(cause);
  }
}
