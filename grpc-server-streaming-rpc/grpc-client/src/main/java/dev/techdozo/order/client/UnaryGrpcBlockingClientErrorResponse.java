package dev.techdozo.order.client;

import dev.techdozo.product.api.ProductServiceGrpc;
import dev.techdozo.product.api.Service.ListProductRequest;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static dev.techdozo.product.api.Service.GetProductResponse.ProductResponseCase.ERROR;
import static dev.techdozo.product.api.Service.GetProductResponse.ProductResponseCase.PRODUCT;

@Slf4j
public class UnaryGrpcBlockingClientErrorResponse {

  private final String host;
  private final int port;

  public UnaryGrpcBlockingClientErrorResponse(String host, int port) {
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

    while (productResponse.hasNext()) {
      var getProductResponse = productResponse.next();
      var productResponseCase = getProductResponse.getProductResponseCase();

      if (productResponseCase == PRODUCT) {
        log.info("Received Product from server, info {}", getProductResponse);
      } else if (productResponseCase == ERROR)
        // Some business logic
        log.info("Received Error {}", getProductResponse.getError());
    }
  }

  public static void main(String[] args) {
    var client = new UnaryGrpcBlockingClientErrorResponse("0.0.0.0", 3000);
    client.callServer();
  }
}
