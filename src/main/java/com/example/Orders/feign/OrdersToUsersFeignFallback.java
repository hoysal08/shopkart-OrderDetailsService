package com.example.Orders.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class OrdersToUsersFeignFallback implements FallbackFactory<OrdersToUserFeign> {
    @Override
    public OrdersToUserFeign create(Throwable throwable) {
        return new OrdersToUserFeign(){

            @Override
            public String retrieveEmail(String userId) {
                return new String("notFound");
            }
        };
    }
}
