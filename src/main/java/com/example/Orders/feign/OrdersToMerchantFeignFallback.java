package com.example.Orders.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class OrdersToMerchantFeignFallback implements FallbackFactory<OrdersToMerchantFeign> {
    @Override
    public OrdersToMerchantFeign create(Throwable throwable) {
        return new OrdersToMerchantFeign() {
            @Override
            public ResponseEntity<Boolean> updateProductsSold(String merchantId, Long stock, String what) {
                return new ResponseEntity<>(Boolean.FALSE,HttpStatus.OK);
            }
        };
    }
}
