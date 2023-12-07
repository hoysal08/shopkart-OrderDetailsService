package com.example.Orders.dto;

import lombok.Data;

@Data
public class Sku {
    private String mId;
    private Long stock;
    private Float price;
    private Float listingPrice;
    private Boolean isActive;
}