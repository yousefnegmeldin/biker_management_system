package com.brightskies.biker_system.order.dto;


import java.time.LocalDate;

public record OrderDto(
        LocalDate orderDate,

        String address,

        double amount,

        String paymentMethod,

        Long customerId

) {
}


