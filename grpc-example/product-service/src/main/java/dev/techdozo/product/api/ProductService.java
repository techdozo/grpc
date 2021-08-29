package dev.techdozo.product.api;

import dev.techdozo.product.api.Resources.GetProductRequest;
import dev.techdozo.product.api.Resources.GetProductResponse;
import dev.techdozo.product.appliction.ProductInfo;
import dev.techdozo.product.appliction.repository.ProductRepository;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class ProductService extends ProductServiceGrpc.ProductServiceImplBase {

  private final ProductRepository productRepository;

  public ProductService() {
    this.productRepository = new ProductRepository();
  }

  @Override
  public void getProduct(
      GetProductRequest request, StreamObserver<GetProductResponse> responseObserver) {

    log.info("Calling product repository..");

    var productId = request.getProductId();
    Optional<ProductInfo> productInfo = productRepository.get(productId);

    if (productInfo.isPresent()) {
      var product = productInfo.get();

      var getProductResponse =
          GetProductResponse.newBuilder()
              .setName(product.getName())
              .setDescription(product.getDescription())
              .setPrice(product.getPrice())
              .build();
      responseObserver.onNext(getProductResponse);
      responseObserver.onCompleted();
    } else {
      responseObserver.onError(new StatusException(Status.NOT_FOUND));
    }

    log.info("Finished calling Product API service..");
  }
}
