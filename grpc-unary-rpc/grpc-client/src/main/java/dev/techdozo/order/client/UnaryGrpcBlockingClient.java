package dev.techdozo.order.client;

import dev.techdozo.order.client.interceptor.GrpcClientRequestInterceptor;
import dev.techdozo.order.client.interceptor.GrpcClientResponseInterceptor;
import dev.techdozo.order.context.UserContext;
import dev.techdozo.product.api.ProductServiceGrpc;
import dev.techdozo.product.api.Service.GetProductRequest;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class UnaryGrpcBlockingClient {

  private final String host;
  private final int port;

  public UnaryGrpcBlockingClient(String host, int port) {
    this.host = host;
    this.port = port;
  }

  public void callServer() {

    log.info("Calling Server..");
    var managedChannel =
        ManagedChannelBuilder.forAddress(host, port)
            .intercept(new GrpcClientResponseInterceptor(), new GrpcClientRequestInterceptor())
            .usePlaintext()
            .build();

    var productServiceBlockingStub = ProductServiceGrpc.newBlockingStub(managedChannel);


    var productRequest = GetProductRequest.newBuilder().setProductId("apple-123").build();

    var productResponse = productServiceBlockingStub.getProduct(productRequest);

    log.info("Received Product from server, info {}", productResponse);
  }

  public static void main(String[] args) {
    // Pseudo code to generate JWT token
    var token = "Bearer " + new Random().nextInt();
    UserContext.setUserContext(token);
    var client = new UnaryGrpcBlockingClient("0.0.0.0", 3000);
    client.callServer();
  }
}
