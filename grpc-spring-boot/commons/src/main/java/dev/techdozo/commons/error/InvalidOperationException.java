package dev.techdozo.commons.error;

public class InvalidOperationException extends ServiceException {

  public InvalidOperationException() {
    super();
  }

  public InvalidOperationException(String message) {
    super(message);
  }

  public InvalidOperationException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidOperationException(Throwable cause) {
    super(cause);
  }
}
