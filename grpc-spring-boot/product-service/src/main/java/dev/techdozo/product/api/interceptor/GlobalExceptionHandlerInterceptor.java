package dev.techdozo.product.api.interceptor;

import com.google.protobuf.Any;
import com.google.rpc.Code;
import com.google.rpc.ErrorInfo;
import dev.techdozo.commons.error.RecordNotFoundException;
import io.grpc.*;
import io.grpc.protobuf.StatusProto;

/**
 * To run this class uncomment bean definition in AppConfig @GrpcGlobalServerInterceptor public
 * GlobalExceptionHandlerInterceptor errorHandler() { return new
 * GlobalExceptionHandlerInterceptor(); }
 */
public class GlobalExceptionHandlerInterceptor implements ServerInterceptor {

  @Override
  public <T, R> ServerCall.Listener<T> interceptCall(
      ServerCall<T, R> serverCall, Metadata headers, ServerCallHandler<T, R> serverCallHandler) {
    ServerCall.Listener<T> delegate = serverCallHandler.startCall(serverCall, headers);
    return new ExceptionHandler<>(delegate, serverCall, headers);
  }

  private static class ExceptionHandler<T, R>
      extends ForwardingServerCallListener.SimpleForwardingServerCallListener<T> {

    private final ServerCall<T, R> delegate;
    private final Metadata headers;

    ExceptionHandler(
        ServerCall.Listener<T> listener, ServerCall<T, R> serverCall, Metadata headers) {
      super(listener);
      this.delegate = serverCall;
      this.headers = headers;
    }

    @Override
    public void onHalfClose() {
      try {
        super.onHalfClose();
      } catch (RuntimeException ex) {
        handleException(ex, delegate, headers);
        throw ex;
      }
    }

    private void handleException(
        RuntimeException exception, ServerCall<T, R> serverCall, Metadata headers) {
      // Catch specific Exception and Process
      if (exception instanceof RecordNotFoundException) {
        var errorMetaData = ((RecordNotFoundException) exception).getErrorMetaData();
        // Build google.rpc.ErrorInfo
        var errorInfo =
            ErrorInfo.newBuilder()
                .setReason("Resource not found")
                .setDomain("Product")
                .putAllMetadata(errorMetaData)
                .build();

        com.google.rpc.Status rpcStatus =
            com.google.rpc.Status.newBuilder()
                .setCode(Code.NOT_FOUND.getNumber())
                .setMessage("Product id not found")
                .addDetails(Any.pack(errorInfo))
                .build();

        var statusRuntimeException = StatusProto.toStatusRuntimeException(rpcStatus);

        var newStatus = Status.fromThrowable(statusRuntimeException);
        // Get metadata from statusRuntimeException
        Metadata newHeaders = statusRuntimeException.getTrailers();

        serverCall.close(newStatus, newHeaders);
      } else {
        serverCall.close(Status.UNKNOWN, headers);
      }
    }
  }
}
