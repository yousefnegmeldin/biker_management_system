package com.brightskies.biker_system.order.dto;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.time.LocalDateTime;

public record OrderDto(
        Long id,

        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        LocalDateTime orderDate,

        String address,

        double amount,

        String paymentMethod,

        Long customerId,

        String bikerName,

        String bikerPhone

) {
    public OrderDto {
      id = (long) -1;
      bikerName = null;
      bikerPhone = null;
    }
}


