package dev.techdozo.api.product.resource;

import dev.techdozo.api.product.application.model.Product;
import dev.techdozo.api.product.application.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
public class ProductGatewayController {

  @Autowired private ProductService productService;

  @PostMapping("/products")
  public ResponseEntity<ProductResponse> createProduct(
      @RequestHeader(value = "userId") String userId, @RequestBody Product product) {

    product.setUserId(userId);
    var productId = productService.createNewProduct(product);
    var productResponse = new ProductResponse(productId);
    return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
  }

  @GetMapping("/products/{productId}")
  public ResponseEntity<Product> getProduct(@PathVariable @NotBlank String productId) {

    var product = productService.getProduct(productId);
    return new ResponseEntity<>(product, HttpStatus.CREATED);
  }
}
