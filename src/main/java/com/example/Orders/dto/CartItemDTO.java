package com.example.Orders.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CartItemDTO {
    private String cartId = UUID.randomUUID().toString();;

    private String productId;

    private String merchantId;

    private Long quantity;

    private Float price;
}
