package dev.techdozo.order.client;

import dev.techdozo.product.api.ProductServiceGrpc;
import dev.techdozo.product.api.Resources.GetProductRequest;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductClient {

  private final ProductServiceGrpc.ProductServiceBlockingStub productServiceBlockingStub;

  public ProductClient(String host, int port) {
    var managedChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
    this.productServiceBlockingStub = ProductServiceGrpc.newBlockingStub(managedChannel);
  }

  public void call() {

    log.info("Calling Server..");

    var productRequest = GetProductRequest.newBuilder().setProductId("apple-123").build();

    var productResponse = productServiceBlockingStub.getProduct(productRequest);

    log.info("Received Product from server, info {}", productResponse);
  }

  public static void main(String[] args) {
    var client = new ProductClient("0.0.0.0", 8081);
    client.call();
  }
}
