package dev.techdozo.product.config;

import dev.techdozo.product.api.interceptor.ExceptionHandler;
import dev.techdozo.product.api.interceptor.GlobalExceptionHandlerInterceptor;
import dev.techdozo.product.api.interceptor.LogInterceptor;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class AppConfig {

  @GrpcGlobalServerInterceptor
  public LogInterceptor logServerInterceptor() {
    return new LogInterceptor();
  }

  @GrpcAdvice
  public ExceptionHandler exceptionHandler() {
    return new ExceptionHandler();
  }

//  @GrpcGlobalServerInterceptor
//  public GlobalExceptionHandlerInterceptor errorHandler() {
//    return new GlobalExceptionHandlerInterceptor();
//  }

}
