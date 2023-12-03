package com.example.Orders.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderDetails {

    @Id
    private String orderId;

    private String merchantId;

    private String productId;

    private String userId;

    private OrderStatus oStatus;

    private Long quantity;

    private Float totalPrice;
}