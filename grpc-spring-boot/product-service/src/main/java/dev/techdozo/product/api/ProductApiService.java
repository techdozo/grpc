package dev.techdozo.product.api;

import dev.techdozo.product.Resources;
import dev.techdozo.product.api.interceptor.LogInterceptor;
import dev.techdozo.product.api.mapper.ProductMapper;
import dev.techdozo.product.appliction.Product;
import dev.techdozo.product.appliction.repository.impl.ProductRepositoryImpl;
import dev.techdozo.product.resource.ProductServiceGrpc;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Slf4j
@GRpcService(interceptors = {LogInterceptor.class})
public class ProductApiService extends ProductServiceGrpc.ProductServiceImplBase {

  @Autowired private ProductRepositoryImpl productRepositoryImpl;

  @Override
  public void getProduct(
      Resources.GetProductRequest request,
      StreamObserver<Resources.GetProductResponse> responseObserver) {

    log.info("Calling Product Repository..");

    String sku = request.getProductId();
    Optional<Product> productInfo = productRepositoryImpl.get(sku);

    if (productInfo.isPresent()) {
      var product = productInfo.get();

      var productApiResponse =
          Resources.GetProductResponse.newBuilder()
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

  @Override
  public void createProduct(
      dev.techdozo.product.Resources.CreateProductRequest request,
      io.grpc.stub.StreamObserver<dev.techdozo.product.Resources.CreateProductResponse>
          responseObserver) {

    log.info("Calling Product Repository..");

    Product product = ProductMapper.MAPPER.map(request);
    String productId = productRepositoryImpl.save(product);

    var productApiResponse =
        Resources.CreateProductResponse.newBuilder().setProductId(productId).build();

    responseObserver.onNext(productApiResponse);
    responseObserver.onCompleted();

    log.info("Saved Product, Id {} ..", productId);
  }
}
