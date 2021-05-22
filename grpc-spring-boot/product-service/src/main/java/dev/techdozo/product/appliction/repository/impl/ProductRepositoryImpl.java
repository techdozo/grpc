package dev.techdozo.product.appliction.repository.impl;

import dev.techdozo.product.appliction.Product;
import dev.techdozo.product.appliction.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

  private Map<String, Product> productStorage;

  public ProductRepositoryImpl() {
    productStorage = new HashMap<>();
  }

  public Optional<Product> get(String productId) {
    return Optional.ofNullable(productStorage.get(productId));
  }

  @Override
  public String save(Product product) {
    var uuid = UUID.randomUUID().toString();
    productStorage.put(uuid,product);
    return uuid;
  }

}
