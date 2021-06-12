package dev.techdozo.api.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"dev.techdozo.api.product", "dev.techdozo.commons"})
public class ProductGatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProductGatewayApplication.class, args);
  }
}
