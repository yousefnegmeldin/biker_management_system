package com.brightskies.biker_system.order.dto;

import com.brightskies.biker_system.store.model.Product;
import com.brightskies.biker_system.store.model.Store;

public record CartItemDto(
        Long id,
        Long productId,
        int quantity,
        Long storeId) {
}
