package com.brightskies.biker_system.order.service;
import com.brightskies.biker_system.customer.model.Customer;
import com.brightskies.biker_system.customer.repository.CustomerRepository;
import com.brightskies.biker_system.order.dto.OrderDto;
import com.brightskies.biker_system.order.dto.OrderMapper;
import com.brightskies.biker_system.order.model.Order;
import com.brightskies.biker_system.order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    OrderRepository orderRepository;
    CustomerRepository customerRepo;
    OrderMapper orderMapper;

    @Autowired
    public OrderService(OrderRepository orderRepository ,CustomerRepository customerRepo, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.customerRepo = customerRepo;
        this.orderMapper = orderMapper;
    }

    public OrderDto createOrder(OrderDto orderDto) {
        Order order = orderMapper.mapToOrder(orderDto);
        Customer customer = customerRepo.findById(order.getCustomer().getId()).orElse(null);
        if(customer == null) {
            // throw exception
        }
        order.setCustomer(customer);
        order = orderRepository.save(order);
        return orderMapper.mapToDto(order);
    }

    public String deleteOrder(Long orderId) {
        if(orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
            return "Order deleted";
        }else
            throw new EntityNotFoundException ("Order not found");
    }
}
