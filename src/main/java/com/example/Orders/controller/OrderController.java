package com.example.Orders.controller;

import com.example.Orders.dto.OrderDetailsDTO;
import com.example.Orders.entity.OrderDetails;
import com.example.Orders.feign.OrdersToMerchantFeign;
import com.example.Orders.feign.OrdersToProductFeign;
import com.example.Orders.feign.OrdersToUserFeign;
import com.example.Orders.helper.GlobalHelper;
import com.example.Orders.service.EmailService;
import com.example.Orders.service.OrderService;
import com.example.Orders.service.impl.OrderNotFoundException;
import com.example.Orders.service.impl.OrderProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrdersToProductFeign ordersToProductFeign;

    @Autowired
    OrdersToMerchantFeign ordersToMerchantFeign;

    @Autowired
    OrdersToUserFeign ordersToUserFeign;

    @Autowired
    EmailService emailService;

    @PostMapping("/add")
    public ResponseEntity<String> addOrders(@RequestBody List<OrderDetailsDTO> orderDetailsDTOSList) throws AddressException {
        try {
            List<OrderDetails> orderDetailsList = new ArrayList<>();
//            System.out.println(orderDetailsDTOSList.size());
            for (OrderDetailsDTO orderDetailsDTO : orderDetailsDTOSList) {
                orderDetailsList.add(GlobalHelper.OrderDetailsDTOToOrderDetails(orderDetailsDTO));
                ordersToProductFeign.updateStockByProductIdandMerchantId(orderDetailsDTO.getProductId(),
                        orderDetailsDTO.getMerchantId(), orderDetailsDTO.getQuantity(), "sold");
//                System.out.println(orderDetailsDTO.getQuantity());
                ordersToMerchantFeign.updateProductsSold(orderDetailsDTO.getMerchantId(), orderDetailsDTO.getQuantity(),
                        "sold");
            }
            boolean success = orderService.addOrders(orderDetailsList);
            String userIdOfuser = orderDetailsDTOSList.get(0).getUserId();
            String userEmail = ordersToUserFeign.retrieveEmail(userIdOfuser).toString();
            System.out.println(userEmail + "Here is the userEmail");
            InternetAddress internetAddress = new InternetAddress(userEmail.trim());
            emailService.sendSimpleMessage(internetAddress.getAddress(), "Your Order From ShopKart");

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
            OrderDetailsDTO orderDetailsDTO = GlobalHelper.OrderDetailsToOrderDetailsDTO(orderDetails);
            return ResponseEntity.ok(orderDetailsDTO);
        } catch (OrderProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (OrderNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateOrder(@RequestBody OrderDetailsDTO inputOrderDetailsDTO) {
        try {
            OrderDetails orderDetails = GlobalHelper.OrderDetailsDTOToOrderDetails(inputOrderDetailsDTO);
            boolean success = orderService.updateOrder(orderDetails);
            ordersToProductFeign.updateStockByProductIdandMerchantId(inputOrderDetailsDTO.getProductId(),
                    inputOrderDetailsDTO.getMerchantId(), inputOrderDetailsDTO.getQuantity(), "update");
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
            List<OrderDetailsDTO> orderDetailsDTOS = new ArrayList<>();
            for (OrderDetails orderDetails : orders) {
                orderDetailsDTOS.add(GlobalHelper.OrderDetailsToOrderDetailsDTO(orderDetails));
            }
            return ResponseEntity.ok(orderDetailsDTOS);
        } catch (OrderProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/byUserId/{userId}")
    public ResponseEntity<?> getOrderByUserId(@PathVariable String userId) {
        try {
            List<OrderDetails> orders = orderService.getOrderByUserId(userId);
            List<OrderDetailsDTO> orderDetailsDTOS = new ArrayList<>();
            for (OrderDetails orderDetails : orders) {
                orderDetailsDTOS.add(GlobalHelper.OrderDetailsToOrderDetailsDTO(orderDetails));
            }
            return ResponseEntity.ok(orderDetailsDTOS);
        } catch (OrderProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/byProductId/{productId}")
    public ResponseEntity<?> getOrderByProductId(@PathVariable String productId) {
        try {
            List<OrderDetails> orders = orderService.getOrderByProductId(productId);
            List<OrderDetailsDTO> orderDetailsDTOS = new ArrayList<>();
            for (OrderDetails orderDetails : orders) {
                orderDetailsDTOS.add(GlobalHelper.OrderDetailsToOrderDetailsDTO(orderDetails));
            }
            return ResponseEntity.ok(orderDetailsDTOS);
        } catch (OrderProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
