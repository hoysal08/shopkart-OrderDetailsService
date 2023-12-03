package com.example.Orders.repository;


import com.example.Orders.entity.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, String> {
    @Query("SELECT c FROM Cart c WHERE c.userId = ?1")
    Cart findByUserId(String userId);

}
