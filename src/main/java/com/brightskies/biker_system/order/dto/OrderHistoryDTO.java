package com.brightskies.biker_system.order.dto;

import com.brightskies.biker_system.store.model.Product;

public record OrderHistoryDTO(Product product, Integer quantity) {
}
