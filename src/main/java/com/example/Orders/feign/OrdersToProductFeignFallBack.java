package com.example.Orders.feign;


import feign.hystrix.FallbackFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class OrdersToProductFeignFallBack implements FallbackFactory<OrdersToProductFeign> {

    @Override
    public OrdersToProductFeign create(Throwable throwable) {
        return new OrdersToProductFeign() {
            @Override
            public ResponseEntity<Boolean> updateStockByProductIdandMerchantId(String productId, String merchantId, Long stock , String what) {
                return new ResponseEntity<>(Boolean.FALSE,HttpStatus.OK);
            }
        };
    }
}
