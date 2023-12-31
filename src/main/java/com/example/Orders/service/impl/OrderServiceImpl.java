package com.example.Orders.service.impl;

import com.example.Orders.entity.OrderDetails;
import com.example.Orders.repository.OrderRepository;
import com.example.Orders.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public boolean addOrders(List<OrderDetails> orderDetailsArray) throws OrderProcessingException {
        try {
            if (orderDetailsArray == null || orderDetailsArray.size() == 0) {
                throw new OrderProcessingException("OrderDetails array is empty or null.");
            }
            for (OrderDetails orderDetails : orderDetailsArray) {
                if (orderDetails == null) {
                    continue;
                }
                OrderDetails order = new OrderDetails();
                order.setOrderId(UUID.randomUUID().toString());
                order.setMerchantId(orderDetails.getMerchantId());
                order.setProductId(orderDetails.getProductId());
                order.setUserId(orderDetails.getUserId());
                order.setOStatus(orderDetails.getOStatus());
                order.setQuantity(orderDetails.getQuantity());
                order.setTotalPrice(orderDetails.getTotalPrice());

                orderRepository.save(order);
            }
            return Boolean.TRUE;
        } catch (OrderProcessingException e) {
            throw new OrderProcessingException("OrderDetails Insertion Error");
        }
    }

    @Override
    public OrderDetails getOrder(String orderId) throws OrderProcessingException, OrderNotFoundException {
        try {
            if (orderId == null || orderId.isEmpty()) {
                throw new OrderProcessingException("Invalid orderId for retrieving the order.");
            }

            Optional<OrderDetails> orderOptional = orderRepository.findById(orderId);

            if (!orderOptional.isPresent()) {
                throw new OrderNotFoundException("Order not found for orderId: " + orderId);
            }
            OrderDetails order = orderOptional.get();
            return order;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateOrder(OrderDetails updatedOrder) throws OrderProcessingException, OrderNotFoundException {
        try {
            if (updatedOrder == null || updatedOrder.getOrderId() == null) {
                throw new OrderProcessingException("Invalid input for updating the order.");
            }

            Optional<OrderDetails> orderOptional = orderRepository.findById(updatedOrder.getOrderId());

            if (!orderOptional.isPresent()) {
                throw new OrderNotFoundException("Order not found for orderId: " + updatedOrder.getOrderId());
            }

            OrderDetails orderToUpdate = orderOptional.get();

            BeanUtils.copyProperties(updatedOrder, orderToUpdate);

            orderRepository.save(orderToUpdate);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public List<OrderDetails> getAllOrders() throws OrderProcessingException {
        try {
            Iterable<OrderDetails> orderDetailsIterable = orderRepository.findAll();

            List<OrderDetails> orderDetailsList = new ArrayList<>();
            orderDetailsIterable.forEach(orderDetailsList::add);

            return orderDetailsList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new OrderProcessingException("An error occurred while fetching orders.");
        }

    }

    @Override
    public List<OrderDetails> getOrderByUserId(String userId) throws OrderProcessingException {
        if (userId == null || userId.isEmpty()) {
            throw new OrderProcessingException("Invalid userId for retrieving orders.");
        }
        List<OrderDetails> orderDetailsList = orderRepository.findByUserId(userId);
        return orderDetailsList;
    }

    @Override
    public List<OrderDetails> getOrderByProductId(String productId) throws OrderProcessingException {
        if (productId == null || productId.isEmpty()) {
            throw new OrderProcessingException("Invalid userId for retrieving orders.");
        }
        List<OrderDetails> orderDetailsList = orderRepository.findByProductId(productId);
        return orderDetailsList;
    }

}
