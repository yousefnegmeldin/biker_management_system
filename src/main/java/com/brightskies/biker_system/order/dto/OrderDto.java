package com.brightskies.biker_system.order.dto;

import java.time.LocalDate;
import java.util.List;

public record OrderDto(
        Long orderId,
        LocalDate orderDate ,

        Long addressId,

        double amount,

        String paymentMethod,

        Long customerId,

        List<CartItemDto> items
) {
}


