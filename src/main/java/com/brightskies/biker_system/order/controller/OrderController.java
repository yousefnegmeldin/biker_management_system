package com.brightskies.biker_system.order.controller;

import com.brightskies.biker_system.general.enums.Zone;
import com.brightskies.biker_system.order.dto.OrderDto;
import com.brightskies.biker_system.order.dto.OrderMapper;
import com.brightskies.biker_system.order.model.Order;
import com.brightskies.biker_system.order.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    public ResponseEntity<?> createOrder (@RequestParam Long addressId , @RequestParam String paymentMethod) {
       try {
           return (new ResponseEntity<> (orderService.createOrder(addressId,paymentMethod), HttpStatus.OK));
       }catch (EntityNotFoundException e) {
           return new ResponseEntity<> (e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
       }catch (NullPointerException e) {
           return new ResponseEntity<> (e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
       }
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<String> cancelOrder(@RequestParam Long orderId) {
        return (new ResponseEntity<>(orderService.cancelOrder(orderId), HttpStatus.OK));
    }

    //Biker and manager only api
    @GetMapping("/getall")
    public ResponseEntity<?> getAllOrders() {
        List<OrderDto> orderDtos = new ArrayList<>();

        for (Order order : orderService.getAllFreeOrders()) {
            orderDtos.add(OrderMapper.mapToDto(order));
        }

        if(orderDtos.isEmpty()) {
            return new ResponseEntity<> ("There are no available orders currently",HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }

    @GetMapping("/zone")
    public ResponseEntity<?> getAllOrdersInZone(@RequestParam Zone zone) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrdersInZone(zone));
    }

    //Biker and manager only api
    @PostMapping("/selectorder")
    public ResponseEntity<?> selectOrder(@RequestParam Long orderId) {
        try {
            return new ResponseEntity<>(orderService.selectOrder(orderId), HttpStatus.OK);
        }catch(EntityNotFoundException e){
            return new ResponseEntity<> (e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
