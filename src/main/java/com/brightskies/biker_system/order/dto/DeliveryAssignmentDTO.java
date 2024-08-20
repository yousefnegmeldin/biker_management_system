package com.brightskies.biker_system.order.dto;

import java.time.LocalDate;

public record DeliveryAssignmentDTO(Long order, Long biker, Long expectedTime) {
}
