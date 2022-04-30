package dev.techdozo.product.interceptor;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GrpcServerRequestInterceptor implements ServerInterceptor {

  @Override
  public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
      ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> next) {

    log.info("Validating user token");
    var userToken = metadata.get(Metadata.Key.of("JWT", Metadata.ASCII_STRING_MARSHALLER));
    validateUserToken(userToken);
    return next.startCall(serverCall, metadata);
  }

  private void validateUserToken(String userToken) {
    // Logic to validate token
  }
}
