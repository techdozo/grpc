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
    return Optional.ofNullable(productStorage.get(productId));
  }

  private Map<String, Product> loadProduct() {
    Map<String, Product> products = new HashMap<>();
    products.put(
        "apple-123",
        Product.builder()
            .productId("apple-123")
            .name("Apple iPhone 12 Pro (128GB)")
            .description("Apple iPhone 12 Pro (128GB) - Graphite")
            .price(1617.29)
            .build());
    products.put(
        "apple-124",
        Product.builder()
            .productId("apple-124")
            .name("Apple iPhone 12 Pro Max (128GB)")
            .description("Apple iPhone 12 Pro (128GB) - Red")
            .price(1752.59)
            .build());
    products.put(
        "apple-125",
        Product.builder()
            .productId("apple-125")
            .name("Apple iPhone 12 Pro Max (128GB)")
            .description("Apple iPhone 12 Pro (128GB) - Blue")
            .price(1701.59)
            .build());
    return products;
  }
}
