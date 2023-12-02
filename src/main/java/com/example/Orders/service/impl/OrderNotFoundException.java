package com.example.Orders.service.impl;

public class OrderNotFoundException extends Throwable {
    public OrderNotFoundException(String s) {
        super(s);
    }
}
