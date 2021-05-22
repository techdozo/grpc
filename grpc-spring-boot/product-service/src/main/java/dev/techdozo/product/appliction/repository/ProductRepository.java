package dev.techdozo.product.appliction.repository;

import dev.techdozo.product.appliction.Product;

import java.util.Optional;

public interface ProductRepository {

    Optional<Product> get(String productId);
    String save(Product product);
}
