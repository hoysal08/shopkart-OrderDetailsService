package com.example.Orders.feign;

import com.example.Orders.dto.ProductDto;
import com.example.Orders.dto.ProductIdDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "orders-to-product-feign" , url = "http://localhost:8099" , fallbackFactory =  OrdersToProductFeignFallBack.class)
public interface OrdersToProductFeign {

    @RequestMapping(method = RequestMethod.PUT , value = "/api/products/{productId}/merchant/{merchantId}/{stock}/{what}")
    ResponseEntity<Boolean> updateStockByProductIdandMerchantId(@PathVariable("productId") String productId , @PathVariable("merchantId") String merchantId , @PathVariable("stock") Long stock , @PathVariable("what") String what);

    @RequestMapping(method = RequestMethod.POST , value = "/api/products/byprodIds" )
    ResponseEntity<List<ProductDto>> getProdbyprodIds (@RequestBody List<ProductIdDto> prodIds);
}
