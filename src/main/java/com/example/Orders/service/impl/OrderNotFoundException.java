package com.example.Orders.service.impl;

public class OrderNotFoundException extends Throwable {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
