package com.example.Orders.repository;

import com.example.Orders.entity.CartItem;
import com.example.Orders.entity.OrderDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<OrderDetails,String>{

    @Query("SELECT o FROM OrderDetails o WHERE o.userId = ?1")
    List<OrderDetails> findByUserId(String userId);

    @Query("SELECT o FROM OrderDetails o WHERE o.productId = ?1")
    List<OrderDetails> findByProductId(String productId);
}
