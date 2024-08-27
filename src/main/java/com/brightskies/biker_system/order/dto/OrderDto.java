package com.brightskies.biker_system.order.dto;


import com.brightskies.biker_system.customer.model.Address;

import java.time.LocalDate;

public record OrderDto(
        LocalDate orderDate ,

        Address address,

        double amount,

        String paymentMethod,

        Long customerId

) {
}


