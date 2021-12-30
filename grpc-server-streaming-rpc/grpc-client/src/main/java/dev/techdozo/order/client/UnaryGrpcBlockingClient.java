package dev.techdozo.order.client;

import dev.techdozo.product.api.ProductServiceGrpc;
import dev.techdozo.product.api.Service.ListProductRequest;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

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
    var managedChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
    var productServiceBlockingStub = ProductServiceGrpc.newBlockingStub(managedChannel);

    var productRequest =
        ListProductRequest.newBuilder()
            .addAllProductId(List.of("apple-123", "apple-128", "apple-125"))
            .build();

    var productResponse = productServiceBlockingStub.listProduct(productRequest);

    try {
      while (productResponse.hasNext()) {
        log.info("Received Product from server, info {}", productResponse.next());
      }
    } catch (StatusRuntimeException statusRuntimeException) {
      log.error("Error, message {}", statusRuntimeException.getMessage());
    }
  }

  public static void main(String[] args) {
    var client = new UnaryGrpcBlockingClient("0.0.0.0", 3000);
    client.callServer();
  }
}
