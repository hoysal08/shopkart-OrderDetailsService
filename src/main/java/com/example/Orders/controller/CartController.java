package com.example.Orders.controller;

import com.example.Orders.dto.CartItemDTO;
import com.example.Orders.entity.Cart;
import com.example.Orders.entity.CartItem;
import com.example.Orders.helper.GlobalHelper;
import com.example.Orders.service.CartService;
import com.example.Orders.service.impl.CartNotFoundException;
import com.example.Orders.service.impl.CartProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/create")
    public ResponseEntity<String> createCart(
            @RequestParam String userId,
            @RequestBody List<CartItemDTO> cartItemDTOs
    ) {
        try {
            List<CartItem> cartItems = new ArrayList<>();
            for (CartItemDTO cartItemDTO : cartItemDTOs) {
                CartItem cartItem = GlobalHelper.CartItemDTOToCartItem(cartItemDTO);
                Cart cart = new Cart();
                cart.setUserId(userId);
                cartItem.setCart(cart);
                cartItem.setCartId(UUID.randomUUID().toString());
                cartItems.add(cartItem);
            }

            boolean success = cartService.createCart(userId, cartItems);
            if (success) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Cart created successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create cart.");
            }
        } catch (CartProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCart(
            @RequestParam String userId,
            @RequestBody List<CartItemDTO> cartItemDTOs
    ) {
        try {
            List<CartItem> cartItems = new ArrayList<>();
            for (CartItemDTO cartItemDTO : cartItemDTOs) {
                cartItems.add(GlobalHelper.CartItemDTOToCartItem(cartItemDTO));
            }
            boolean success = cartService.updateCart(userId, cartItems);
            if (success) {
                return ResponseEntity.ok("Cart updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update cart.");
            }
        } catch (CartProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (CartNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getCart(@PathVariable String userId) {
        try {
            List<CartItem> cartItems = cartService.getCart(userId);
            List<CartItemDTO> cartItemDTOS = new ArrayList<>();
            for (CartItem cartItem : cartItems) {
                cartItemDTOS.add(GlobalHelper.CartToCartDTO(cartItem));
            }
            return ResponseEntity.ok(cartItemDTOS);
        } catch (CartProcessingException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (CartNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
