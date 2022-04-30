package dev.techdozo.product.interceptor;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GrpcServerResponseInterceptor implements ServerInterceptor {

  @Override
  public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
      ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> next) {

    return next.startCall(
        new ForwardingServerCall.SimpleForwardingServerCall<>(serverCall) {
          @Override
          public void sendMessage(RespT message) {
            log.info("Message being sent to client : " + message);
            super.sendMessage(message);
          }
        },
        metadata);
  }
}
