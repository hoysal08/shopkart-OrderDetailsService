package com.example.Orders.service.impl;

public class CartNotFoundException extends Throwable {
    public CartNotFoundException(String message) {
        super(message);
    }
}
