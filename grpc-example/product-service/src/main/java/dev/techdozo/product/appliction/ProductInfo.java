package dev.techdozo.product.appliction;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductInfo {
    private String sku;
    private String name;
    private String description;
    private double price;
}
