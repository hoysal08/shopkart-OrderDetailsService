package com.example.Orders.service;

import com.example.Orders.dto.ProductDto;
import com.example.Orders.dto.ProductIdDto;
import com.example.Orders.entity.CartItem;
import com.example.Orders.service.impl.CartNotFoundException;
import com.example.Orders.service.impl.CartProcessingException;

import java.util.List;

public interface CartService {
    boolean createCart(String userId, List<CartItem> cartItems) throws CartProcessingException;

    boolean updateCart(String userId, List<CartItem> newCartItems) throws CartProcessingException, CartNotFoundException;

    List<CartItem> getCart(String userId) throws CartProcessingException, CartNotFoundException;

    boolean deleteCartItem (String userId , String cartId);


    boolean createOrUpdateCart(String userId, CartItem newCartItems) throws CartProcessingException, CartNotFoundException;

    List<ProductDto> getProductByProductIds (List<ProductIdDto> prodId);
}
