package com.brightskies.biker_system.order.dto;
import com.brightskies.biker_system.customer.repository.CustomerRepository;
import com.brightskies.biker_system.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        return new OrderDto(
                order.getOrderDate(),
                order.getAddress().getId(),
                order.getAmount(),
                order.getPaymentMethod(),
                order.getCustomer().getId()
                );
    }

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
                        order.getOrderDate(),
                        order.getAddress(),
                        order.getAmount(),
                        order.getPaymentMethod(),
                        order.getCustomer().getId() // assuming Address has a label field
                ))
                .collect(Collectors.toList());
    }

}
