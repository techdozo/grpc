package dev.techdozo.product.interceptor;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

/**
 * gRPC server interceptor with ThreadLocal
 */
@Slf4j
public class GrpcMDCInterceptor implements ServerInterceptor {

  private static final String TRACE_ID = "traceId";

  @Override
  public <R, S> ServerCall.Listener<R> interceptCall(
      ServerCall<R, S> serverCall, Metadata metadata, ServerCallHandler<R, S> next) {

    log.info("Setting user context, metadata {}", metadata);

    var traceId = metadata.get(Metadata.Key.of("traceId", Metadata.ASCII_STRING_MARSHALLER));

    MDC.put(TRACE_ID, traceId);

    try {
      return new WrappingListener<>(next.startCall(serverCall, metadata), traceId);
    } finally {
      MDC.clear();
    }
  }

  private static class WrappingListener<R>
      extends ForwardingServerCallListener.SimpleForwardingServerCallListener<R> {
    private final String traceId;

    public WrappingListener(ServerCall.Listener<R> delegate, String traceId) {
      super(delegate);
      this.traceId = traceId;
    }

    @Override
    public void onMessage(R message) {
      MDC.put(TRACE_ID, traceId);
      try {
        super.onMessage(message);
      } finally {
        MDC.clear();
      }
    }

    @Override
    public void onHalfClose() {
      MDC.put(TRACE_ID, traceId);
      try {
        super.onHalfClose();
      } finally {
        MDC.clear();
      }
    }

    @Override
    public void onCancel() {
      MDC.put(TRACE_ID, traceId);
      try {
        super.onCancel();
      } finally {
        MDC.clear();
      }
    }

    @Override
    public void onComplete() {
      MDC.put(TRACE_ID, traceId);
      try {
        super.onComplete();
      } finally {
        MDC.clear();
      }
    }

    @Override
    public void onReady() {
      MDC.put(TRACE_ID, traceId);
      try {
        super.onReady();
      } finally {
        MDC.clear();
      }
    }
  }
}
