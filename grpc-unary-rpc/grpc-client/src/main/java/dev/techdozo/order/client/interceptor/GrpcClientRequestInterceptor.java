package dev.techdozo.order.client.interceptor;

import dev.techdozo.order.context.UserContext;
import io.grpc.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GrpcClientRequestInterceptor implements ClientInterceptor {

  public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
      final MethodDescriptor<ReqT, RespT> methodDescriptor,
      final CallOptions callOptions,
      final Channel channel) {

    return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(
        channel.newCall(methodDescriptor, callOptions)) {

      @Override
      public void start(ClientCall.Listener<RespT> responseListener, Metadata headers) {
        var userToken = UserContext.getUserContext().getUserToken();
        log.info("Setting userToken {} in header", userToken);
        headers.put(Metadata.Key.of("JWT", Metadata.ASCII_STRING_MARSHALLER), userToken);
        super.start(responseListener, headers);
      }
    };
  }
}
