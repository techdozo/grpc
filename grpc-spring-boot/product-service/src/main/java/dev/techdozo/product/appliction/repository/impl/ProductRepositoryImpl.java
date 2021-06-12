package dev.techdozo.product.appliction.repository.impl;

import dev.techdozo.commons.error.ErrorCode;
import dev.techdozo.commons.error.ResourceNotFoundException;
import dev.techdozo.product.appliction.Product;
import dev.techdozo.product.appliction.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

  private final Map<String, Product> productStorage;

  public ProductRepositoryImpl() {
    productStorage = new HashMap<>();
  }

  public Product get(String productId) {
    var product = Optional.ofNullable(productStorage.get(productId));

    return product.orElseThrow(
        () ->
            new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND,
                "Product ID not found",
                Map.of("resource_id", productId, "message", "Product ID not found")));
  }

  @Override
  public String save(Product product) {
    var uuid = UUID.randomUUID().toString();
    productStorage.put(uuid, product);
    return uuid;
  }
}
