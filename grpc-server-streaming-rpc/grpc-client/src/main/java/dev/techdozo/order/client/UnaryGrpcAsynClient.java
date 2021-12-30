package dev.techdozo.order.client;

import dev.techdozo.product.api.ProductServiceGrpc;
import dev.techdozo.product.api.Service;
import dev.techdozo.product.api.Service.GetProductResponse;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

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
    var managedChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
    // Create a new async stub
    var productServiceAsyncStub = ProductServiceGrpc.newStub(managedChannel);

    var productRequest =
        Service.ListProductRequest.newBuilder()
            .addAllProductId(List.of("apple-123", "apple-124", "apple-125"))
            .build();

    productServiceAsyncStub.listProduct(productRequest, new ProductCallback());

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
    var client = new UnaryGrpcAsynClient("0.0.0.0", 3000);
    client.callServer();
  }
}
