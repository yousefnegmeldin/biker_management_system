package com.brightskies.biker_system.order.controller;
import com.brightskies.biker_system.general.enums.Zone;
import com.brightskies.biker_system.order.dto.OrderDto;
import com.brightskies.biker_system.order.dto.OrderMapper;
import com.brightskies.biker_system.order.model.CartItem;
import com.brightskies.biker_system.order.model.Order;
import com.brightskies.biker_system.order.model.OrderHistory;
import com.brightskies.biker_system.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER')")
    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestParam Long addressId, @RequestParam String paymentMethod) {
        return (new ResponseEntity<>(orderService.createOrder(addressId, paymentMethod), HttpStatus.OK));
    }
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER','ROLE_MANAGER','ROLE_ADMIN')")
    @DeleteMapping("/cancel")
    //check that if customer is cancelling, that orderid corresponds to the customer
    public ResponseEntity<String> cancelOrder(@RequestParam Long orderId) {
        return (new ResponseEntity<>(orderService.cancelOrder(orderId), HttpStatus.OK));
    }

    //Biker and manager only api
    @PreAuthorize("hasAnyRole('ROLE_BIKER','ROLE_MANAGER','ROLE_ADMIN')")
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
            return new ResponseEntity<>("There are no available orders currently.", HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }


    @GetMapping("/zone")
    @PreAuthorize("hasAnyRole('ROLE_BIKER','ROLE_MANAGER','ROLE_ADMIN')")
    public ResponseEntity<?> getAllOrdersInZone(@RequestParam Zone zone) {
        List<OrderDto> orderDTOs = OrderMapper.toDTOList(orderService.getOrdersInZone(zone));
        if (orderDTOs.isEmpty()) {
            return new ResponseEntity<>("There are no available orders in that zone currently.", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderDTOs, HttpStatus.OK);
    }
}
