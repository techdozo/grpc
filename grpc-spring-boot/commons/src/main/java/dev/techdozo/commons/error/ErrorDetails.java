package dev.techdozo.commons.error;

import lombok.Getter;

public class ErrorDetails {
  @Getter
  public enum Code {
    RESOURCE_ID("err_resource_id"),
    MESSAGE("err_message");

    private final String key;

    Code(String key) {
      this.key = key;
    }
  }
}
