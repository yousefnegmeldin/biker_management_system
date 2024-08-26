package com.brightskies.biker_system.order.dto;
import com.brightskies.biker_system.order.model.Order;

public class OrderDtoMapper {

    public static OrderDto mapToDto (Order order) {
        return new OrderDto(
                order.getId(),
                order.getOrderDate(),
                order.getAddress(),
                order.getAmount(),
                order.getPaymentMethod(),
                order.getCustomer().getId(),
                "",
                ""
                );
    }

    public static Order mapToOrder (OrderDto orderdto) {
        return new Order(
                (long) -1,
                orderdto.orderDate(),
                orderdto.address(),
                orderdto.amount(),
                orderdto.paymentMethod(),
                null,
                null
        );
    }

}
