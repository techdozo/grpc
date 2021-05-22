package dev.techdozo.api.product.application.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Product {
    private String name;
    private String description;
    private double price;
    private String userId;
}
