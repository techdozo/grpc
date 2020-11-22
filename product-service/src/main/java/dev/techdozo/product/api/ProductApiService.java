package dev.techdozo.product.api;

import dev.techdozo.product.appliction.ProductInfo;
import dev.techdozo.product.appliction.repository.ProductRepository;
import io.grpc.Status;
import io.grpc.StatusException;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static dev.techdozo.product.api.Product.ProductApiRequest;
import static dev.techdozo.product.api.Product.ProductApiResponse;

@Slf4j
public class ProductApiService extends ProductApiServiceGrpc.ProductApiServiceImplBase {

  private ProductRepository productRepository;

  public ProductApiService() {
    this.productRepository = new ProductRepository();
  }

  @Override
  public void getProduct(
      ProductApiRequest request, io.grpc.stub.StreamObserver<ProductApiResponse> responseObserver) {

    log.info("Calling Product Repository..");

    String sku = request.getSku();
    Optional<ProductInfo> productInfo = productRepository.get(sku);

    if (productInfo.isPresent()) {
      ProductInfo product = productInfo.get();
      ProductApiResponse productApiResponse =
          ProductApiResponse.newBuilder()
              .setName(product.getName())
              .setDescription(product.getDescription())
              .setPrice(product.getPrice())
              .build();
      responseObserver.onNext(productApiResponse);
      responseObserver.onCompleted();
    } else {
      responseObserver.onError(new StatusException(Status.NOT_FOUND));
    }

    log.info("Finished calling Product API service..");
  }
}
