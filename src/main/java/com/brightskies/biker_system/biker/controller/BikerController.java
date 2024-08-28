package com.brightskies.biker_system.biker.controller;

import com.brightskies.biker_system.biker.dto.BikerDto;
import com.brightskies.biker_system.biker.service.BikerService;
import com.brightskies.biker_system.feedback.service.FeedBackService;
import com.brightskies.biker_system.order.dto.OrderDto;
import com.brightskies.biker_system.order.dto.OrderMapper;
import com.brightskies.biker_system.order.enums.AssignmentStatus;
import com.brightskies.biker_system.order.model.CartItem;
import com.brightskies.biker_system.order.model.Order;
import com.brightskies.biker_system.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/biker")
public class BikerController {
    private FeedBackService feedBackService;
    private BikerService bikerService;
    private OrderService orderService;

    @Autowired
    public BikerController(FeedBackService feedBackService, BikerService bikerService, OrderService orderService) {
        this.feedBackService = feedBackService;
        this.bikerService = bikerService;
        this.orderService = orderService;
    }


    //should be orderDTO
    @GetMapping("/orders/free")
    public ResponseEntity<?> getAllFreeOrders() {
        return ResponseEntity.ok(orderService.getAllFreeOrders());
    }

    @PostMapping("/orders/accept/{orderId}")
    public ResponseEntity<Void> acceptOrder(@PathVariable long orderId) throws Exception {
        bikerService.acceptOrder(orderId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/assignment/{deliveryAssignmentId}/status")
    public ResponseEntity<Void> updateAssignmentStatus(@PathVariable Long deliveryAssignmentId, @RequestParam AssignmentStatus status) throws Exception {
        bikerService.updateAssignmentStatus(deliveryAssignmentId, status);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/orders/{orderId}/cart-items")
//    public ResponseEntity<List<CartItem>> getCartItemsForOrder(@PathVariable Long orderId) {
//        return ResponseEntity.ok(bikerService.getCartItemsForUser(orderId));
//    }

    @PutMapping("/orders/deliver")
    public ResponseEntity<Void> deliverOrder() throws Exception {
        bikerService.deliverOrder();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/rating")
    public ResponseEntity<Double> getBikerRating() {
        return ResponseEntity.ok(bikerService.getBikerRating());
    }
}
