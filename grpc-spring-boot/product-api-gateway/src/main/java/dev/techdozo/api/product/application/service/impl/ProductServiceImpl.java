package dev.techdozo.api.product.application.service.impl;

import dev.techdozo.api.product.application.mapper.ProductMapper;
import dev.techdozo.api.product.application.model.Product;
import dev.techdozo.api.product.application.service.ProductService;
import dev.techdozo.product.Resources.CreateProductRequest;
import dev.techdozo.product.Resources.GetProductRequest;
import dev.techdozo.product.resource.ProductServiceGrpc;
import io.grpc.ManagedChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductServiceImpl implements ProductService {

  private ManagedChannel managedChannel;

  public ProductServiceImpl(ManagedChannel managedChannel) {
    this.managedChannel = managedChannel;
  }

  @Override
  public String createNewProduct(Product product) {
    log.info("Calling Server..");

    var createProductRequest =
        CreateProductRequest.newBuilder()
            .setPrice(product.getPrice())
            .setName(product.getName())
            .setDescription(product.getDescription())
            .setUserId(product.getUserId())
            .build();

    var productApiServiceBlockingStub = ProductServiceGrpc.newBlockingStub(managedChannel);

    var response = productApiServiceBlockingStub.createProduct(createProductRequest);

    var productId = response.getProductId();

    log.info("Received Product information from product service, info {}", product);

    return productId;
  }

  @Override
  public Product get(String productId) {

    log.info("Calling Server..");

    var getProductRequest = GetProductRequest.newBuilder().setProductId(productId).build();
    var productApiServiceBlockingStub =
        ProductServiceGrpc.newBlockingStub(managedChannel);

    var response = productApiServiceBlockingStub.getProduct(getProductRequest);

    var product = ProductMapper.MAPPER.map(response);

    log.info("Received Product information from product service, info {}", product);

    return product;
  }
}
