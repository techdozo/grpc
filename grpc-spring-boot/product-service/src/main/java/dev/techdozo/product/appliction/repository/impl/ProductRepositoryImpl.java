package dev.techdozo.product.appliction.repository.impl;

import dev.techdozo.product.appliction.Product;
import dev.techdozo.product.appliction.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

  private Map<String, Product> productStorage;

  public ProductRepositoryImpl() {
    productStorage = loadProduct();
  }

  public Optional<Product> get(String sku) {
    return Optional.ofNullable(productStorage.get(sku));
  }

  private Map<String, Product> loadProduct() {
    Map<String, Product> products = new HashMap<>();
    products.put(
        "apple-123",
        Product.builder()
            .sku("abc")
            .name("Apple iPhone 12 Pro (128GB)")
            .description("Apple iPhone 12 Pro (128GB) - Graphite")
            .price(1617.29)
            .build());
    products.put(
        "apple-124",
        Product.builder()
            .sku("abc")
            .name("Apple iPhone 12 Pro Max (128GB)")
            .description("Apple iPhone 12 Pro (128GB) - Graphite")
            .price(1752.59)
            .build());
    return products;
  }
}
