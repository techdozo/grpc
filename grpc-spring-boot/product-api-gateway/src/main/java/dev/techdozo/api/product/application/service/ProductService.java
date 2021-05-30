package dev.techdozo.api.product.application.service;


import dev.techdozo.api.product.application.model.Product;

public interface ProductService {
    String createNewProduct(Product product);
    Product getProduct(String productId);
}
