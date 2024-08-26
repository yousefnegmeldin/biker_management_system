package com.brightskies.biker_system.order.dto;

import com.brightskies.biker_system.order.model.CartItem;

public class CartItemDtoMapper {

    public static CartItemDto map (CartItem cartItem) {
        return new CartItemDto(
                cartItem.getProduct(),
                cartItem.getQuantity(),
                cartItem.getStore());
    }
}
