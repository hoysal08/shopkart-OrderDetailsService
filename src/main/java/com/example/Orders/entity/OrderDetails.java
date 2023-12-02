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

    public static final String SEQ_GEN_ALIAS = "seq_gen_alias";
    public static final String SEQ_GEN_STRATEGY = "uuid2";

    @Id
    @GeneratedValue(generator = OrderDetails.SEQ_GEN_ALIAS)
    @GenericGenerator(name = OrderDetails.SEQ_GEN_ALIAS, strategy = OrderDetails.SEQ_GEN_STRATEGY)
    private String orderId;

    private String merchantId;

    private String productId;

    private String userId;

    private OrderStatus oStatus;

    private Long quantity;

    private Float totalPrice;
}