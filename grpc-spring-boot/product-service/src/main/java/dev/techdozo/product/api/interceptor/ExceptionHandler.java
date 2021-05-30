package dev.techdozo.product.api.interceptor;

import com.google.protobuf.Any;
import com.google.rpc.Code;
import com.google.rpc.ErrorInfo;
import dev.techdozo.commons.error.RecordNotFoundException;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.StatusProto;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class ExceptionHandler {

  @GrpcExceptionHandler(RecordNotFoundException.class)
  public StatusRuntimeException handleResourceNotFoundException(RecordNotFoundException cause) {

    var errorMetaData = cause.getErrorMetaData();

    var errorInfo =
        ErrorInfo.newBuilder()
            .setReason("Resource not found")
            .setDomain("Product")
            .putAllMetadata(errorMetaData)
            .build();

    var status =
        com.google.rpc.Status.newBuilder()
            .setCode(Code.NOT_FOUND.getNumber())
            .setMessage("Resource not found")
            .addDetails(Any.pack(errorInfo))
            .build();

    return StatusProto.toStatusRuntimeException(status);
  }

  @GrpcExceptionHandler
  public Status handleInvalidArgument(IllegalArgumentException e) {
    return Status.INVALID_ARGUMENT.withDescription("Your description").withCause(e);
  }
}
