package dev.techdozo.product.appliction;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Product {
  private String productId;
  private String name;
  private String description;
  private double price;
  private String userId;
}
