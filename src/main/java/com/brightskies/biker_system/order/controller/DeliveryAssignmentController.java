package com.brightskies.biker_system.order.controller;

import com.brightskies.biker_system.order.dto.DeliveryAssignmentDTO;
import com.brightskies.biker_system.order.dto.DeliveryAssignmentMapper;
import com.brightskies.biker_system.order.enums.AssignmentStatus;
import com.brightskies.biker_system.order.service.DeliveryAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceNotFoundException;

@RequestMapping("/delivery-assignment")
@RestController
public class DeliveryAssignmentController {
    private DeliveryAssignmentService deliveryAssignmentService;

    @Autowired
    public DeliveryAssignmentController(DeliveryAssignmentService deliveryAssignmentService) {
        this.deliveryAssignmentService = deliveryAssignmentService;
    }

    @PostMapping()
    public ResponseEntity<?> addDeliveryAssignment(@RequestBody DeliveryAssignmentDTO deliveryAssignmentDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    DeliveryAssignmentMapper.toDTO(
                            deliveryAssignmentService.addDeliveryAssignment(deliveryAssignmentDTO)
                    )
            );
        }
        catch(IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
        }
        catch(Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @PatchMapping("/status/{id}")
    public ResponseEntity<?> updateDeliveryAssignmentStatus(@PathVariable Long id, @RequestParam AssignmentStatus status) {
        try {
            deliveryAssignmentService.changeStatus(id, status);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }
}
