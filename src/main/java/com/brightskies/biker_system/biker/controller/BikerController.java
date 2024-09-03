package com.brightskies.biker_system.biker.controller;


import com.brightskies.biker_system.biker.service.BikerService;
import com.brightskies.biker_system.order.dto.*;
import com.brightskies.biker_system.order.enums.AssignmentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/biker")
@PreAuthorize("hasAnyRole('ROLE_BIKER')")
public class BikerController {
    private BikerService bikerService;

    @Autowired
    public BikerController( BikerService bikerService) {
        this.bikerService = bikerService;
    }

    @GetMapping("/orders/free")
    public ResponseEntity<List<OrderDto>> getAllFreeOrders() {
        return ResponseEntity.ok(OrderMapper.toDTOList(bikerService.getOrdersInZone()));
    }

    @PostMapping("/orders/accept/{orderId}")
    public ResponseEntity<?> acceptOrder(@PathVariable long orderId) throws Exception {
        return ResponseEntity.ok(DeliveryAssignmentMapper.toDTO(bikerService.acceptOrder(orderId)));
    }

    @PutMapping("/assignment/{deliveryAssignmentId}")
    public ResponseEntity<?> updateAssignmentStatus(@PathVariable Long deliveryAssignmentId, @RequestParam AssignmentStatus status) throws Exception {
        bikerService.updateAssignmentStatus(deliveryAssignmentId, status);
        return ResponseEntity.ok("Updated assignment status successfully.");
    }

    @GetMapping("/orders/{orderId}/cart-items")
    public ResponseEntity<List<OrderHistoryDTO>> getCartItemsForOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(
                OrderHistoryMapper.toDTOList(
                        bikerService.getCartItemsForUser(orderId)
                )
        );
    }

    @PutMapping("/orders/deliver")
    public ResponseEntity<?> deliverOrder(@RequestParam Long deliveryAssignmentId) throws Exception {
        bikerService.deliverOrder(deliveryAssignmentId);
        return ResponseEntity.ok("Delivered order successfully!");
    }

    @GetMapping("/rating")
    public ResponseEntity<Double> getBikerRating() {
        return ResponseEntity.ok(bikerService.getBikerRating());
    }
}
