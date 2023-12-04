package com.example.Orders.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "orders-to-user-feign" , url = "http://localhost:8051" , fallbackFactory =  OrdersToUsersFeignFallback.class)
public interface OrdersToUserFeign {

    @RequestMapping(method = RequestMethod.GET, value = "/api/users/get-email/{userId}")
    String retrieveEmail(@PathVariable("userId") String userId);

}