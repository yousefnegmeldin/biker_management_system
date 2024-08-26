package com.brightskies.biker_system.order.controller;

import com.brightskies.biker_system.order.dto.OrderDto;
import com.brightskies.biker_system.order.dto.DtoMapper;
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

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        Order order = DtoMapper.mapToOrder(orderDto);
        return (new ResponseEntity<> (DtoMapper.mapToDto(orderService.createOrder(order)), HttpStatus.OK));
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        return (new ResponseEntity<>(orderService.deleteOrder(orderId), HttpStatus.OK));
    }
}
