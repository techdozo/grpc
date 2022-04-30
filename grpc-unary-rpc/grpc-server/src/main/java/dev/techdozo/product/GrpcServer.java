package dev.techdozo.product;

import dev.techdozo.product.api.ProductService;
import dev.techdozo.product.interceptor.GrpcServerRequestInterceptor;
import dev.techdozo.product.interceptor.GrpcServerResponseInterceptor;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Slf4j
public class GrpcServer {

  private final int port;
  private final Server server;

  public GrpcServer(int port) {
    this.port = port;
    var productService = new ProductService();
    this.server =
        ServerBuilder.forPort(port)
            .addService(
                ServerInterceptors.intercept(
                    productService,
                    new GrpcServerResponseInterceptor(),
                    new GrpcServerRequestInterceptor()))
            .build();
  }

  public void start() throws IOException {
    log.info("Starting Server..");

    server.start();

    log.info("Server Started on port {} ", port);

    Runtime.getRuntime()
        .addShutdownHook(
            new Thread(
                () -> {
                  try {
                    this.stop();
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                }));
  }

  private void stop() throws InterruptedException {
    log.info("Stopping Server..");

    if (server != null) {
      server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
    }
  }

  private void blockUntilShutDown() throws InterruptedException {
    if (this.server != null) {
      server.awaitTermination();
    }
  }

  public static void main(String[] args) throws IOException, InterruptedException {
    var productServer = new GrpcServer(3000);
    productServer.start();
    productServer.blockUntilShutDown();
  }
}
