package com.brightskies.biker_system.order.dto;

public record DeliveryAssignmentDTO(Long order, Long biker, Long expectedTime) {
}
