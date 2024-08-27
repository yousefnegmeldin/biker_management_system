package com.brightskies.biker_system.order.dto;


import com.brightskies.biker_system.order.model.CartItem;

import java.time.LocalDate;
import java.util.List;

public record OrderDto(
        LocalDate orderDate ,

        Long addressId,

        double amount,

        String paymentMethod,

        Long customerId,

        List<CartItem> items
) {
}


