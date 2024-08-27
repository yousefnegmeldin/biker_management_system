package com.brightskies.biker_system.order.service;
import com.brightskies.biker_system.authentication.utility.SecurityUtils;
import com.brightskies.biker_system.biker.model.Biker;
import com.brightskies.biker_system.biker.repository.BikerRepository;
import com.brightskies.biker_system.customer.model.Customer;
import com.brightskies.biker_system.customer.repository.AddressRepository;
import com.brightskies.biker_system.customer.repository.CustomerRepository;
import com.brightskies.biker_system.general.enums.Zone;
import com.brightskies.biker_system.order.dto.DeliveryAssignmentDTO;
import com.brightskies.biker_system.order.dto.OrderDto;
import com.brightskies.biker_system.order.dto.OrderMapper;
import com.brightskies.biker_system.order.model.CartItem;
import com.brightskies.biker_system.order.model.DeliveryAssignment;
import com.brightskies.biker_system.order.model.Order;
import com.brightskies.biker_system.order.repository.CartRepository;
import com.brightskies.biker_system.order.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final BikerRepository bikerRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepo;
    private final AddressRepository addressRepo;
    private final CartRepository cartRepository;
    private final CartService cartService;
    private final DeliveryAssignmentService deliveryAssignmentService;;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        CustomerRepository customerRepo,
                        AddressRepository addressRepo,
                        CartService cartService,
                        BikerRepository bikerRepository,
                        CartRepository cartRepository,
                        DeliveryAssignmentService deliveryAssignmentService) {
        this.orderRepository = orderRepository;
        this.customerRepo = customerRepo;
        this.addressRepo = addressRepo;
        this.cartService = cartService;
        this.bikerRepository = bikerRepository;
        this.cartRepository = cartRepository;
        this.deliveryAssignmentService = deliveryAssignmentService;
    }

    public OrderDto createOrder(Long addressId, String paymentMethod) {
        Long currentCustomerId = SecurityUtils.getCurrentUserId();
        Customer customer = customerRepo.findById(currentCustomerId).
                orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setCustomer(customer);
        order.setAddress(addressRepo.findById(addressId).
                orElseThrow(() -> new EntityNotFoundException("Address not found")));

        List<CartItem> items = cartRepository.findByCustomerId(currentCustomerId);
        if(!items.isEmpty()) {
            order.setItems(items);
            cartService.deleteAll();
            double total = items.stream()
                    .mapToDouble(item -> item.getProduct().getPrice())
                    .sum();
            order.setAmount(total);
        }else {
            throw new NullPointerException("Cart is empty!");
        }

        order.setPaymentMethod(paymentMethod);
        order = orderRepository.save(order);
        return OrderMapper.mapToDto(order);
    }

    public String cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).
                orElseThrow(() -> new EntityNotFoundException("Order not found"));

        if (order.getBiker() == null) {
            orderRepository.deleteById(orderId);
            return "Order has been cancelled successfully.";
        }
        else {
            return "Order has been assigned to a biker and can't be cancelled, " +
                    "please contact support.";
        }
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAllFreeOrders();
    }

    public List<Order> getOrdersInZone(Zone zone) {
        return orderRepository.findAllFreeOrdersInZone(zone);
    }

    public DeliveryAssignment selectOrder(Long orderId) throws Exception {
        Long currentBikerId = SecurityUtils.getCurrentUserId();
        Order order = orderRepository.findById(orderId).
                orElseThrow(() -> new EntityNotFoundException("Order not found"));
        Biker biker = bikerRepository.findById(currentBikerId).
                orElseThrow(() -> new EntityNotFoundException("biker not found"));
        return deliveryAssignmentService.addDeliveryAssignment(new DeliveryAssignmentDTO(
                order.getId(),
                biker.getId(),
                30L
        ));
    }
}
