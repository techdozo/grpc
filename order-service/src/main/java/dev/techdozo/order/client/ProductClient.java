package dev.techdozo.order.client;

import dev.techdozo.product.api.ProductApiServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

import static dev.techdozo.product.api.Product.ProductApiRequest;
import static dev.techdozo.product.api.Product.ProductApiResponse;

@Slf4j
public class ProductClient {

  private final ManagedChannel managedChannel;
  private final ProductApiServiceGrpc.ProductApiServiceBlockingStub productApiServiceBlockingStub;

  public ProductClient(String host, int port) {
    this.managedChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
    this.productApiServiceBlockingStub = ProductApiServiceGrpc.newBlockingStub(this.managedChannel);
  }

  public void call() {
    log.info("Calling Server..");

    ProductApiRequest productApiRequest =
        ProductApiRequest.newBuilder().setSku("apple-123").build();

    ProductApiResponse product = productApiServiceBlockingStub.getProduct(productApiRequest);

    log.info("Received Product information from product service, info {}", product);
  }

  public static void main(String[] args) {
    ProductClient client = new ProductClient("0.0.0.0", 8080);
    client.call();
  }
}
