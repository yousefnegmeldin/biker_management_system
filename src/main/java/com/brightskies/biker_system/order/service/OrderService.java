package com.brightskies.biker_system.order.service;
import com.brightskies.biker_system.authentication.utility.SecurityUtils;
import com.brightskies.biker_system.biker.model.Biker;
import com.brightskies.biker_system.biker.repository.BikerRepository;
import com.brightskies.biker_system.customer.model.Address;
import com.brightskies.biker_system.customer.model.Customer;
import com.brightskies.biker_system.customer.repository.AddressRepository;
import com.brightskies.biker_system.customer.repository.CustomerRepository;
import com.brightskies.biker_system.exception.model.*;
import com.brightskies.biker_system.general.enums.Zone;
import com.brightskies.biker_system.order.dto.DeliveryAssignmentDTO;
import com.brightskies.biker_system.order.dto.OrderDto;
import com.brightskies.biker_system.order.dto.OrderMapper;
import com.brightskies.biker_system.order.model.CartItem;
import com.brightskies.biker_system.order.model.DeliveryAssignment;
import com.brightskies.biker_system.order.model.Order;
import com.brightskies.biker_system.order.model.OrderHistory;
import com.brightskies.biker_system.order.repository.CartRepository;
import com.brightskies.biker_system.order.repository.OrderHistoryRepository;
import com.brightskies.biker_system.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private final BikerRepository bikerRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepo;
    private final AddressRepository addressRepo;
    private final CartRepository cartRepository;
    private final CartService cartService;
    private final OrderHistoryRepository orderHistoryRepository;
    private final DeliveryAssignmentService deliveryAssignmentService;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        CustomerRepository customerRepo,
                        AddressRepository addressRepo,
                        CartService cartService,
                        BikerRepository bikerRepository,
                        CartRepository cartRepository,
                        OrderHistoryRepository orderHistoryRepository,
                        DeliveryAssignmentService deliveryAssignmentService) {
        this.orderRepository = orderRepository;
        this.customerRepo = customerRepo;
        this.addressRepo = addressRepo;
        this.cartService = cartService;
        this.bikerRepository = bikerRepository;
        this.cartRepository = cartRepository;
        this.orderHistoryRepository = orderHistoryRepository;
        this.deliveryAssignmentService = deliveryAssignmentService;
    }

    public void addToOrderHistory(List<CartItem> items, Order order) {
        for(CartItem item : items) {
            OrderHistory orderHistory = new OrderHistory(order, item.getProduct(), item.getQuantity(),item.getStore());
            orderHistoryRepository.save(orderHistory);
        }
    }

    public List<OrderHistory> getItemsByOrderId(Long orderId){
        return orderHistoryRepository.findByOrderId(orderId);
    }

    public OrderDto createOrder(Long addressId, String paymentMethod) throws AccessDeniedException {
        Long currentCustomerId = SecurityUtils.getCurrentUserId();
        Customer customer = customerRepo.findById(currentCustomerId).
                orElseThrow(() -> new CustomerNotFoundException(currentCustomerId));
        Address address = addressRepo.findById(addressId).
                orElseThrow(() -> new AddressNotFoundException(addressId));
        if (!address.getCustomer().getId().equals(currentCustomerId)){
            throw new AccessDeniedException("You are not authorized to access this address.");
        }
        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setCustomer(customer);
        order.setAddress(addressRepo.findById(addressId).
                orElseThrow(() -> new AddressNotFoundException(addressId)));

        List<CartItem> items = cartRepository.findByCustomerId(currentCustomerId);

        if(!items.isEmpty()) {
            order.setItems(items);
            cartService.deleteAllCartItemsAfterCreatingOrder();
            double total = items.stream()
                    .mapToDouble(item -> item.getProduct().getPrice()*item.getQuantity())
                    .sum();
            order.setAmount(total);
        }else {
            throw new EmptyCartException();
        }

        order.setPaymentMethod(paymentMethod);
        order = orderRepository.save(order);
        addToOrderHistory(items, order);
        return OrderMapper.mapToDto(order);
    }

    public List<OrderHistory> getCartItemsForCurrentOrder(Long orderId) {
        return orderHistoryRepository.findByOrderId(orderId);
    }

    public String cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).
                orElseThrow(() -> new OrderNotFoundException(orderId));

        if (order.getBiker() == null) {
            orderHistoryRepository.deleteByOrderId(orderId);
            orderRepository.deleteById(orderId);

            return "Order has been cancelled successfully.";
        }
        else {
            return "Order has been assigned to a biker and can't be cancelled, " +
                    "please contact support.";
        }
    }

    public List<Order> getAllFreeOrders() {
        return orderRepository.findAllFreeOrders();
    }

    public List<Order> getOrdersInZone(Zone zone) {
        return orderRepository.findAllFreeOrdersInZone(zone);
    }

    public DeliveryAssignment selectOrder(Long orderId) throws Exception {
        Long currentBikerId = SecurityUtils.getCurrentUserId();
        Order order = orderRepository.findById(orderId).
                orElseThrow(() -> new OrderNotFoundException(orderId));
        Biker biker = bikerRepository.findById(currentBikerId).
                orElseThrow(() -> new BikerNotFoundException(currentBikerId));
        return deliveryAssignmentService.addDeliveryAssignment(new DeliveryAssignmentDTO(
                0L,
                order.getId(),
                biker.getId(),
                30L
        ));
    }
}
