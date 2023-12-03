package com.example.Orders.service.impl;

import com.example.Orders.entity.Cart;
import com.example.Orders.entity.CartItem;
import com.example.Orders.repository.CartRepository;
import com.example.Orders.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public boolean createCart(String userId, List<CartItem> cartItems) throws CartProcessingException {
        try {
            if (userId == null || userId.isEmpty() || cartItems == null || cartItems.isEmpty()) {
                throw new CartProcessingException("Invalid input for creating a cart.");
            }
            Cart cart = new Cart();
            cart.setUserId(userId);

            for (CartItem cartItem : cartItems) {
                cartItem.setCart(cart);
            }
            cart.setCartItems(cartItems);
            cartRepository.save(cart);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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

        List<CartItem> existingCartItem = existingCart.getCartItems();

        Cart cart = new Cart();
        cart.setUserId(userId);

        for (CartItem cartItem : newCartItems) {
            cartItem.setCart(cart);
            existingCartItem.add(cartItem);
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
