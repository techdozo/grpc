package dev.techdozo.api.product.application.config;

import dev.techdozo.api.product.application.service.ProductService;
import dev.techdozo.api.product.application.service.impl.ProductServiceImpl;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public ManagedChannel managedChannel() {
    return ManagedChannelBuilder.forAddress(
            applicationProperties().getHost(), applicationProperties().getPort())
        .usePlaintext()
        .build();
  }

  @Bean
  public ProductService productService() {
    return new ProductServiceImpl(managedChannel());
  }

  @Bean
  public ApplicationProperties applicationProperties() {
    return new ApplicationProperties();
  }
}
