package com.brightskies.biker_system.order.dto;
import com.brightskies.biker_system.customer.repository.CustomerRepository;
import com.brightskies.biker_system.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

//    public Order mapToEntity (OrderDto orderdto) {
//        Order order = new Order();
//        order.setOrderDate(orderdto.orderDate());
//        order.setAddress(orderdto.address());
//        order.setAmount(orderdto.amount());
//        order.setPaymentMethod(orderdto.getPaymentMethod());
//        order.setCustomer(customerRepository.findById(orderdto.getCustomerId()).orElseThrow(() -> new RuntimeException("Customer not found")));
//        return order;
//    }

}
