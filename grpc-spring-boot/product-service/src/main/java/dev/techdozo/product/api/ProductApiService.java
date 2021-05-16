package dev.techdozo.product.api;

import dev.techdozo.product.api.interceptor.LogInterceptor;
import dev.techdozo.product.appliction.Product;
import dev.techdozo.product.appliction.repository.impl.ProductRepositoryImpl;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static dev.techdozo.product.api.Product.ProductApiRequest;
import static dev.techdozo.product.api.Product.ProductApiResponse;

@Slf4j
@GRpcService(interceptors = { LogInterceptor.class })
public class ProductApiService extends ProductApiServiceGrpc.ProductApiServiceImplBase {

  @Autowired private ProductRepositoryImpl productRepositoryImpl;

  @Override
  public void getProduct(
      ProductApiRequest request, StreamObserver<ProductApiResponse> responseObserver) {

    log.info("Calling Product Repository..");

    String sku = request.getSku();
    Optional<Product> productInfo = productRepositoryImpl.get(sku);

    if (productInfo.isPresent()) {
      var product = productInfo.get();

      var productApiResponse =
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
