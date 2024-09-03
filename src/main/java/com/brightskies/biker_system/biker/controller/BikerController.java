package com.brightskies.biker_system.biker.controller;


import com.brightskies.biker_system.biker.service.BikerService;
import com.brightskies.biker_system.order.dto.*;
import com.brightskies.biker_system.order.enums.AssignmentStatus;
import io.swagger.v3.oas.annotations.Operation;
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


    @Operation(summary = "Get all free orders",
            description = "Retrieve a list of all free orders available in the biker's zone.")
    @GetMapping("/orders/free")
    public ResponseEntity<List<OrderDto>> getAllFreeOrders() {
        return ResponseEntity.ok(OrderMapper.toDTOList(bikerService.getOrdersInZone()));
    }

    @Operation(summary = "Accept an order",
            description = "Accept a specific order by its ID.")
    @PostMapping("/orders/accept/{orderId}")
    public ResponseEntity<?> acceptOrder(@PathVariable long orderId) throws Exception {
        return ResponseEntity.ok(DeliveryAssignmentMapper.toDTO(bikerService.acceptOrder(orderId)));
    }

    @Operation(summary = "Update assignment status",
            description = "Update the status of a specific delivery assignment.")
    @PutMapping("/assignment/{deliveryAssignmentId}/status")
    public ResponseEntity<?> updateAssignmentStatus(@PathVariable Long deliveryAssignmentId, @RequestParam AssignmentStatus status) throws Exception {
        bikerService.updateAssignmentStatus(deliveryAssignmentId, status);
        return ResponseEntity.ok("Updated assignment status successfully.");
    }

    @Operation(summary = "Get cart items for an order",
            description = "Retrieve the cart items for a specific order by its ID.")
    @GetMapping("/orders/{orderId}/cart-items")
    public ResponseEntity<List<OrderHistoryDTO>> getCartItemsForOrder(@PathVariable Long orderId) {
        return ResponseEntity.ok(
                OrderHistoryMapper.toDTOList(
                        bikerService.getCartItemsForUser(orderId)
                )
        );
    }

    @Operation(summary = "Deliver an order",
            description = "Mark a specific order as delivered.")
    @PutMapping("/orders/deliver")
    public ResponseEntity<?> deliverOrder(@RequestParam Long deliveryAssignmentId) throws Exception {
        bikerService.deliverOrder(deliveryAssignmentId);
        return ResponseEntity.ok("Delivered order successfully!");
    }

    @Operation(summary = "Get biker rating",
            description = "Retrieve the rating of the current biker.")
    @GetMapping("/rating")
    public ResponseEntity<Double> getBikerRating() {
        return ResponseEntity.ok(bikerService.getBikerRating());
    }
}
