package com.brightskies.biker_system.order.dto;

public record DeliveryAssignmentDTO(Long assignmentId,Long order, Long biker, Long expectedTime) {
}
