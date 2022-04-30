package dev.techdozo.order.client;

import dev.techdozo.order.client.interceptor.GrpcClientRequestInterceptor;
import dev.techdozo.order.context.UserContext;
import dev.techdozo.product.api.ProductServiceGrpc;
import dev.techdozo.product.api.Service.GetProductRequest;
import dev.techdozo.product.api.Service.GetProductResponse;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class UnaryGrpcAsynClient {

  private final String host;
  private final int port;

  public UnaryGrpcAsynClient(String host, int port) {
    this.host = host;
    this.port = port;
  }

  @SneakyThrows
  public void callServer() {

    log.info("Calling Server..");
    var managedChannel = ManagedChannelBuilder.forAddress(host, port)
            .intercept(new GrpcClientRequestInterceptor()).usePlaintext().build();


    // Create a new async stub
    var productServiceAsyncStub = ProductServiceGrpc.newStub(managedChannel);

    var productRequest = GetProductRequest.newBuilder().setProductId("apple-123").build();

    productServiceAsyncStub.getProduct(productRequest, new ProductCallback());

    Thread.sleep(3000);

    log.info("Finished call");
  }

  private static class ProductCallback implements StreamObserver<GetProductResponse> {

    @Override
    public void onNext(GetProductResponse value) {
      log.info("Received product, {}", value);
    }

    @Override
    public void onError(Throwable cause) {
      log.error("Error occurred, cause {}", cause.getMessage());
    }

    @Override
    public void onCompleted() {
      log.info("Stream completed");
    }
  }

  public static void main(String[] args) {
    //Pseudo code to generate JWT token
    var token = "Bearer " + new Random().nextInt();
    UserContext.setUserContext(token);
    var client = new UnaryGrpcAsynClient("0.0.0.0", 3000);
    client.callServer();
  }
}
