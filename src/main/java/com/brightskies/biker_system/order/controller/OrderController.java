package com.brightskies.biker_system.order.controller;

import com.brightskies.biker_system.order.dto.OrderDto;
import com.brightskies.biker_system.order.dto.OrderMapper;
import com.brightskies.biker_system.order.model.Order;
import com.brightskies.biker_system.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
@RestController
public class OrderController {

    OrderService orderService;
    OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping()
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        return (new ResponseEntity<> (orderService.createOrder(orderDto), HttpStatus.OK));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        return (new ResponseEntity<>(orderService.deleteOrder(orderId), HttpStatus.OK));
    }
}
