package com.brightskies.biker_system.order.dto;
import com.brightskies.biker_system.customer.repository.CustomerRepository;
import com.brightskies.biker_system.order.model.CartItem;
import com.brightskies.biker_system.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    CustomerRepository customerRepository;

    @Autowired
    public OrderMapper(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static OrderDto mapToDto (Order order) {
        List<CartItem> cartItems = order.getItems();
        List<CartItemDto> cartItemDtos = new ArrayList<>();
        if(cartItems == null) {
            cartItems = new ArrayList<>();
        }
        for (CartItem item : cartItems) {
            cartItemDtos.add(CartItemDtoMapper.map(item));
        }
        return new OrderDto(
                order.getId(),
                order.getOrderDate(),
                order.getAddress().getId(),
                order.getAmount(),
                order.getPaymentMethod(),
                order.getCustomer().getId(),
                cartItemDtos
                );
    }

//    public Order mapToEntity (OrderDto orderdto) {
//        Order order = new Order();
//        order.setOrderDate(orderdto.orderDate());
//        order.setAddress(orderdto.address());
//        order.setAmount(orderdto.amount());
//        order.setPaymentMethod(orderdto.getPaymentMethod());
//        order.setCustomer(customerRepository.findById(orderdto.getCustomerId()).orElseThrow(() -> new RuntimeException("Customer not found")));
//        return order;
//    }
    /*public Order mapToOrder (OrderDto orderdto) {
        return new Order(
                (long) -1,
                orderdto.orderDate(),
                orderdto.address(),
                orderdto.amount(),
                orderdto.paymentMethod(),
                null,
                customerRepository.findById(orderdto.customerId()).orElseThrow(() -> new RuntimeException("Customer does not exist"))
        );
    }*/

    public static List<OrderDto> toDTOList(List<Order> orders) {
        return orders.stream()
                .map(order -> new OrderDto(
                        order.getId(),
                        order.getOrderDate(),
                        order.getAddress().getId(),
                        order.getAmount(),
                        order.getPaymentMethod(),
                        order.getCustomer().getId(),
                        new ArrayList<CartItemDto>()// assuming Address has a label field
                ))
                .collect(Collectors.toList());
    }

}
