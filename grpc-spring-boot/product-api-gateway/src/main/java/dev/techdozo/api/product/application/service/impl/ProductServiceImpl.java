package dev.techdozo.api.product.application.service.impl;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.rpc.ErrorInfo;
import dev.techdozo.api.product.application.mapper.ProductMapper;
import dev.techdozo.api.product.application.model.Product;
import dev.techdozo.api.product.application.service.ProductService;
import dev.techdozo.commons.error.ServiceException;
import dev.techdozo.product.Resources.CreateProductRequest;
import dev.techdozo.product.Resources.GetProductRequest;
import dev.techdozo.product.resource.ProductServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
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
  public Product getProduct(String productId) {

    log.info("Calling Server..");
    Product product;
    try {
      var request = GetProductRequest.newBuilder().setProductId(productId).build();
      var productApiServiceBlockingStub = ProductServiceGrpc.newBlockingStub(managedChannel);
      var response = productApiServiceBlockingStub.getProduct(request);
      // Map to domain object
      product = ProductMapper.MAPPER.map(response);
      log.info("Received Product information from product service, info {}", product);
    } catch (StatusRuntimeException error) {

      var status = io.grpc.protobuf.StatusProto.fromThrowable(error);
      ErrorInfo errorInfo = null;

      for (Any any : status.getDetailsList()) {
        if (!any.is(ErrorInfo.class)) {
          continue;
        }
        try {
          errorInfo = any.unpack(ErrorInfo.class);
        } catch (InvalidProtocolBufferException cause) {
          log.error("Error parsing protobuf");
        }
      }
      log.info("Error while calling product service, reason {} ", errorInfo.getReason());

      throw new ServiceException(errorInfo.getReason(), errorInfo.getMetadataMap());
    }

    return product;
  }
}
