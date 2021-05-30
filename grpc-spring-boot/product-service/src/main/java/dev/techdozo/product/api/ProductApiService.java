package dev.techdozo.product.api;

import dev.techdozo.product.Resources.CreateProductRequest;
import dev.techdozo.product.Resources.CreateProductResponse;
import dev.techdozo.product.Resources.GetProductRequest;
import dev.techdozo.product.Resources.GetProductResponse;
import dev.techdozo.product.api.mapper.ProductMapper;
import dev.techdozo.product.appliction.repository.ProductRepository;
import dev.techdozo.product.resource.ProductServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@GrpcService
public class ProductApiService extends ProductServiceGrpc.ProductServiceImplBase {

  @Autowired private ProductRepository productRepository;

  @Override
  public void createProduct(
      CreateProductRequest request, StreamObserver<CreateProductResponse> responseObserver) {

    log.info("Calling Product Repository..");

    var product = ProductMapper.MAPPER.map(request);
    var productId = productRepository.save(product);

    var createProductResponse = CreateProductResponse.newBuilder().setProductId(productId).build();

    responseObserver.onNext(createProductResponse);
    responseObserver.onCompleted();

    log.info("Saved Product, Id {} ..", productId);
  }

  @Override
  public void getProduct(
      GetProductRequest request, StreamObserver<GetProductResponse> responseObserver) {

    log.info("Calling Product Repository..");

    String productId = request.getProductId();

    var product = productRepository.get(productId);

    var response =
        GetProductResponse.newBuilder()
            .setName(product.getName())
            .setDescription(product.getDescription())
            .setPrice(product.getPrice())
            .setUserId(product.getUserId())
            .build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();

    log.info("Finished calling Product API service..");
  }
}
