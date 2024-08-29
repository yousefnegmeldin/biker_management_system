package com.brightskies.biker_system.order.dto;


public record CartItemDto(
        Long id,
        Long productId,
        int quantity,
        Long storeId) {
}
