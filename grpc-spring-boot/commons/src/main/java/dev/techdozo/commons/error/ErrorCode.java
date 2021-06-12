package dev.techdozo.commons.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

@Getter
public enum ErrorCode {

  /** Specified resource not found */
  RESOURCE_NOT_FOUND("ResourceNotFound", "Resource not found", HttpStatus.NOT_FOUND),
  BAD_ARGUMENT("BadArgument", "Bad argument", HttpStatus.BAD_REQUEST),
  INVALID_OPERATION("InvalidOperation", "Operation not allowed", HttpStatus.PRECONDITION_FAILED);

  private final String shortCode;

  private final String message;

  private final HttpStatus httpStatus;


  ErrorCode(String shortCode, String message, HttpStatus httpStatus) {
    this.shortCode = shortCode;
    this.message = message;
    this.httpStatus = httpStatus;
  }

  public static ErrorCode errorCode(String shortCode) {
    var errorCode = resolve(shortCode);
    if (errorCode == null) {
      throw new IllegalArgumentException("No matching constant for [" + shortCode + "]");
    }
    return errorCode;
  }

  @Nullable
  public static ErrorCode resolve(String shortCode) {
    // used cached VALUES instead of values() to prevent array allocation
    for (ErrorCode errorCode : values()) {
      if (errorCode.shortCode.equals(shortCode)) {
        return errorCode;
      }
    }
    return null;
  }
}
