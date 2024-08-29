package com.brightskies.biker_system.order.dto;

import com.brightskies.biker_system.order.model.DeliveryAssignment;

public class DeliveryAssignmentMapper {
        public static DeliveryAssignmentDTO toDTO (DeliveryAssignment deliveryAssignment) {
            return new DeliveryAssignmentDTO(
                    deliveryAssignment.getId(),
                    deliveryAssignment.getOrder().getId(),
                    deliveryAssignment.getBiker().getId(),
                    deliveryAssignment.getExpectedTime()
            );
        }
}
