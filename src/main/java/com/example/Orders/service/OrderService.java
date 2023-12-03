package com.example.Orders.service;

import com.example.Orders.entity.OrderDetails;
import com.example.Orders.service.impl.OrderNotFoundException;
import com.example.Orders.service.impl.OrderProcessingException;

import java.util.List;

public interface OrderService {
    boolean addOrders(List<OrderDetails> orderDetailsArray) throws OrderProcessingException;

    OrderDetails getOrder(String orderId) throws OrderProcessingException, OrderNotFoundException;

    boolean updateOrder(OrderDetails inputOrderDetails) throws OrderProcessingException, OrderNotFoundException;

    List<OrderDetails> getAllOrders() throws OrderProcessingException;

    List<OrderDetails> getOrderByUserId(String userId) throws OrderProcessingException;

    List<OrderDetails> getOrderByProductId(String productId) throws OrderProcessingException;
}
