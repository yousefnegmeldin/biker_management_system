package com.brightskies.biker_system.order.controller;
import com.brightskies.biker_system.general.enums.Zone;
import com.brightskies.biker_system.order.dto.OrderDto;
import com.brightskies.biker_system.order.dto.OrderMapper;
import com.brightskies.biker_system.order.model.CartItem;
import com.brightskies.biker_system.order.model.Order;
import com.brightskies.biker_system.order.model.OrderHistory;
import com.brightskies.biker_system.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/order")
@RestController

public class OrderController {

    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(
            summary = "Creates an order",
            description = "Creates an order for current user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = OrderDto.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Customer doesn't exist or address is invalid",
                            content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(
                            responseCode = "406",
                            description = "Order not created as cart is empty",
                            content = @Content(schema = @Schema(implementation = String.class))),
            }
    )
    @PreAuthorize("hasAnyRole('ROLE_customer')")
    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestParam Long addressId, @RequestParam String paymentMethod) throws AccessDeniedException {
        return (new ResponseEntity<>(orderService.createOrder(addressId, paymentMethod), HttpStatus.OK));
    }

    @Operation(
            summary = "Cancels an order",
            description = "Cancels an order for current user if a biker hasn't been assigned yet",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Order doesn't exist",
                            content = @Content(schema = @Schema(implementation = String.class))),
            }
    )
    @PreAuthorize("hasAnyRole('ROLE_customer','ROLE_manager','ROLE_admin')")
    @DeleteMapping("/cancel")
    public ResponseEntity<String> cancelOrder(@RequestParam Long orderId) {
        return (new ResponseEntity<>(orderService.cancelOrder(orderId), HttpStatus.OK));
    }

    @Operation(
            summary = "Gets all available orders",
            description = "Gets all unassigned orders, only biker, manager and admin can access this api",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(schema = @Schema(type = "array", implementation = OrderDto.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No free orders are available",
                            content = @Content(schema = @Schema(implementation = String.class))),
            }
    )
    @PreAuthorize("hasAnyRole('ROLE_biker','ROLE_manager','ROLE_admin')")
    @GetMapping("/getall")
    public ResponseEntity<?> getAllOrders() {
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : orderService.getAllFreeOrders()) {
            List<OrderHistory> orderHistories = orderService.getItemsByOrderId(order.getId());
            List<CartItem> cartItems = new ArrayList<>();
            for(OrderHistory orderHistory : orderHistories) {
                cartItems.add(new CartItem(
                        orderHistory.getId(),
                        order.getCustomer(),
                        orderHistory.getProduct(),
                        orderHistory.getQuantity(),
                        orderHistory.getStore()
                        ));
            }
            order.setItems(cartItems);
            orderDtos.add(OrderMapper.mapToDto(order));
        }

        if (orderDtos.isEmpty()) {
            return new ResponseEntity<>("There are no available orders currently.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }


    @Operation(
            summary = "Gets all available orders in the same zone as biker",
            description = "Gets all unassigned orders in the same zone of the biker, only biker, manager and admin are able to use this api",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(schema = @Schema(type = "array", implementation = OrderDto.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No free orders are available in the required zone",
                            content = @Content(schema = @Schema(implementation = String.class))),
            }
    )
    @GetMapping("/zone")
    @PreAuthorize("hasAnyRole('ROLE_biker','ROLE_manager','ROLE_admin')")
    public ResponseEntity<?> getAllOrdersInZone(@RequestParam Zone zone) {
        List<OrderDto> orderDTOs = OrderMapper.toDTOList(orderService.getOrdersInZone(zone));
        if (orderDTOs.isEmpty()) {
            return new ResponseEntity<>("There are no available orders in that zone currently.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderDTOs, HttpStatus.OK);
    }
}
