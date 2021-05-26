package dev.techdozo.product.appliction.repository;

import dev.techdozo.product.appliction.Product;

public interface ProductRepository {

  /**
   * Get product based on the productId. Throws RecordNotFoundException if product id is not found.
   *
   * @param productId id of the product.
   * @return Product
   */
  Product get(String productId);

  String save(Product product);
}
