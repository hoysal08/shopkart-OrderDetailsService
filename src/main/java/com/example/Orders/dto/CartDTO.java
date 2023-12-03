package com.example.Orders.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {

    private String userId;
    private List<CartDTO> cartItems;
}
