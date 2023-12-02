package com.example.Orders.dto;

import com.example.Orders.entity.OrderStatus;


public class OrderDetailsDTO {
    private String orderId;

    private String merchantId;

    private String productId;

    private String userId;

    private OrderStatus oStatus;

    private Long quantity;

    private Float total_Price;
}