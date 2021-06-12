package dev.techdozo.commons.error;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@Setter
@Getter
@Data
@JsonTypeInfo(include = As.WRAPPER_OBJECT, use = Id.NAME, visible = true)
@JsonTypeName("error")
public class ErrorResponse {
  private ErrorCode errorCode;
  private String message;
  private Map<String, String> details;
}
