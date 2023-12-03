package com.example.Orders.helper;

import com.example.Orders.dto.CartDTO;
import com.example.Orders.dto.CartItemDTO;
import com.example.Orders.dto.OrderDetailsDTO;
import com.example.Orders.entity.Cart;
import com.example.Orders.entity.CartItem;
import com.example.Orders.entity.OrderDetails;
import org.springframework.beans.BeanUtils;

public class GlobalHelper {

    public static OrderDetailsDTO OrderDetailsToOrderDetailsDTO(OrderDetails orderDetails) {
        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
        BeanUtils.copyProperties(orderDetails, orderDetailsDTO);
        return orderDetailsDTO;
    }

    public static CartDTO CartToCartDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        BeanUtils.copyProperties(cart, cartDTO);
        return cartDTO;
    }

    public static OrderDetails OrderDetailsDTOToOrderDetails(OrderDetailsDTO orderDetailsDTO) {
        OrderDetails orderDetails = new OrderDetails();
        BeanUtils.copyProperties(orderDetails, orderDetailsDTO);
        return orderDetails;
    }

    public static Cart CartDTOToCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        BeanUtils.copyProperties(cartDTO, cart);
        return cart;
    }


    public static CartItem CartItemDTOToCartItem(CartItemDTO cartItemDTO) {
        CartItem CartItem = new CartItem();
        BeanUtils.copyProperties(cartItemDTO, CartItem);
        return CartItem;
    }

    public static CartItemDTO CartToCartDTO(CartItem cartItem) {
        CartItemDTO cartItemDTO = new CartItemDTO();
        BeanUtils.copyProperties(cartItem, cartItemDTO);
        return cartItemDTO;
    }

}
