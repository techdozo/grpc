package dev.techdozo.product.api.interceptor;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogInterceptor implements ServerInterceptor {
    @Override
    public <R, T> ServerCall.Listener<R> interceptCall(
            ServerCall<R, T> call, Metadata headers, ServerCallHandler<R, T> next) {
        log.info(call.getMethodDescriptor().getFullMethodName());
        return next.startCall(call, headers);
    }
}
