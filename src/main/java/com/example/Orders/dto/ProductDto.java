package com.example.Orders.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String productId ;

    private String productName;
    private List<String> productImageURL;
    private String usp;
    private Categories category;
    private List<Sku> skus;
    private String description;
    private Attribute attribute;
    private List<Review> reviews;
    private Float ratings;
}
