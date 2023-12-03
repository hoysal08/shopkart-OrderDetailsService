package com.example.Orders.controller;

import com.example.Orders.entity.OrderDetails;
import com.example.Orders.service.OrderService;
import com.example.Orders.service.impl.OrderNotFoundException;
import com.example.Orders.service.impl.OrderProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<String> addOrders(@RequestBody List<OrderDetails> orderDetailsList) {
        try {
            boolean success = orderService.addOrders(orderDetailsList);
            if (success) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Orders added successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add orders.");
            }
        } catch (OrderProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable String orderId) {
        try {
            OrderDetails orderDetails = orderService.getOrder(orderId);
            return ResponseEntity.ok(orderDetails);
        } catch (OrderProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (OrderNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateOrder(@RequestBody OrderDetails inputOrderDetails) {
        try {
            boolean success = orderService.updateOrder(inputOrderDetails);
            if (success) {
                return ResponseEntity.ok("Order updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update order.");
            }
        } catch (OrderProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (OrderNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllOrders() {
        try {
            List<OrderDetails> orders = orderService.getAllOrders();
            return ResponseEntity.ok(orders);
        } catch (OrderProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/byUserId/{userId}")
    public ResponseEntity<?> getOrderByUserId(@PathVariable String userId) {
        try {
            List<OrderDetails> orders = orderService.getOrderByUserId(userId);
            return ResponseEntity.ok(orders);
        } catch (OrderProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/byProductId/{productId}")
    public ResponseEntity<?> getOrderByProductId(@PathVariable String productId) {
        try {
            List<OrderDetails> orders = orderService.getOrderByProductId(productId);
            return ResponseEntity.ok(orders);
        } catch (OrderProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
