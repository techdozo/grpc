package dev.techdozo.commons.error;

public enum Code {

  /** Specified resource not found */
  RESOURCE_NOT_FOUND("Resource Not Found");

  private String errorCode;

  Code(String errorCode) {
    this.errorCode = errorCode;
  }
}
