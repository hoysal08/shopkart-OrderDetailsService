package com.example.Orders.feign;


import com.example.Orders.dto.ProductDto;
import com.example.Orders.dto.ProductIdDto;
import feign.hystrix.FallbackFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrdersToProductFeignFallBack implements FallbackFactory<OrdersToProductFeign> {

    @Override
    public OrdersToProductFeign create(Throwable throwable) {
        return new OrdersToProductFeign() {
            @Override
            public ResponseEntity<Boolean> updateStockByProductIdandMerchantId(String productId, String merchantId, Long stock , String what) {
                return new ResponseEntity<>(Boolean.FALSE,HttpStatus.OK);
            }

            @Override
            public ResponseEntity<List<ProductDto>> getProdbyprodIds(List<ProductIdDto> prodIds) {
                return new ResponseEntity<>(new ArrayList<>() , HttpStatus.OK);
            }
        };
    }
}
