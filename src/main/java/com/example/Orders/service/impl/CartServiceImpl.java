package com.example.Orders.service.impl;

import com.example.Orders.entity.Cart;
import com.example.Orders.entity.CartItem;
import com.example.Orders.repository.CartRepository;
import com.example.Orders.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;

    @Override
    public boolean createCart(String userId, List<CartItem> cartItems) throws CartProcessingException {
        if (userId == null || userId.isEmpty() || cartItems == null || cartItems.isEmpty()) {
            throw new CartProcessingException("Invalid input for creating a cart.");
        }
        Cart cart = new Cart();
        cart.setUserId(userId);

        for (CartItem cartItem : cartItems) {
            cartItem.setCart(cart);
        }
        cartRepository.save(cart);

        return true;
    }

    @Override
    public boolean updateCart(String userId, List<CartItem> newCartItems) throws CartProcessingException, CartNotFoundException {
        if (userId == null || userId.isEmpty() || newCartItems == null) {
            throw new CartProcessingException("Invalid input for updating the cart.");
        }

        Cart existingCart = cartRepository.findByUserId(userId);

        if (existingCart == null) {
            throw new CartNotFoundException("Cart not found for userId: " + userId);
        }

        existingCart.getCartItems().clear();

        for (CartItem newCartItem : newCartItems) {
            newCartItem.setCart(existingCart);
        }
        cartRepository.save(existingCart);
        return true;
    }

    @Override
    public List<CartItem> getCart(String userId) throws CartProcessingException, CartNotFoundException {
        if (userId == null || userId.isEmpty()) {
            throw new CartProcessingException("Invalid userId for retrieving the cart.");
        }
        Cart cart = cartRepository.findByUserId(userId);

        if (cart == null) {
            throw new CartNotFoundException("Cart not found for userId: " + userId);
        }

        List<CartItem> cartItems = cart.getCartItems();
        return cartItems;
    }
}
