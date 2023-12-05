package com.example.Orders.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "orders-to-merchant-feign" , url = "http://localhost:8091" , fallbackFactory =  OrdersToMerchantFeignFallback.class)
public interface OrdersToMerchantFeign {

    @RequestMapping(method = RequestMethod.PUT , value = "/merchant/{merchantId}/update-sold/{stock}/{what}")
    ResponseEntity<Boolean> updateProductsSold (@PathVariable("merchantId") String merchantId, @PathVariable("stock") Long stock, @PathVariable("what") String what);
}
