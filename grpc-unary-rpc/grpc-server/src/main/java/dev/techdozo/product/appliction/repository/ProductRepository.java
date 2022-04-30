package dev.techdozo.product.appliction.repository;

import dev.techdozo.product.appliction.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProductRepository {

  private final Map<String, Product> productStorage;

  public ProductRepository() {
    productStorage = loadProduct();
  }

  public Optional<Product> get(String productId) {
    var product =
        Product.builder()
            .productId(productId)
            .name("Apple iPhone 12 Pro (128GB) - " + productId)
            .description("Apple iPhone 12 Pro (128GB) - Graphite - " + productId)
            .price(1617.29)
            .build();
    return Optional.ofNullable(product);
  }

  private Map<String, Product> loadProduct() {
    Map<String, Product> products = new HashMap<>();
    products.put(
        "apple-123",
        Product.builder()
            .productId("abc")
            .name("Apple iPhone 12 Pro (128GB)")
            .description("Apple iPhone 12 Pro (128GB) - Graphite")
            .price(1617.29)
            .build());
    products.put(
        "apple-124",
        Product.builder()
            .productId("abc")
            .name("Apple iPhone 12 Pro Max (128GB)")
            .description("Apple iPhone 12 Pro (128GB) - Graphite")
            .price(1752.59)
            .build());
    return products;
  }
}
