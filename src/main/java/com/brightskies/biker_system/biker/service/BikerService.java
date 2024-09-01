package com.brightskies.biker_system.biker.service;

import com.brightskies.biker_system.authentication.utility.SecurityUtils;
import com.brightskies.biker_system.biker.dto.BikerDto;
import com.brightskies.biker_system.biker.model.Biker;
import com.brightskies.biker_system.biker.repository.BikerRepository;
import com.brightskies.biker_system.exception.model.BikerNotFoundException;
import com.brightskies.biker_system.exceptions.UserExceptions.EmailExistsException;
import com.brightskies.biker_system.exception.model.DeliveryAssignmentNotFoundException;
import com.brightskies.biker_system.exceptions.UserExceptions.EmailExistsException;
import com.brightskies.biker_system.order.enums.AssignmentStatus;
import com.brightskies.biker_system.order.model.DeliveryAssignment;
import com.brightskies.biker_system.order.model.Order;
import com.brightskies.biker_system.order.model.OrderHistory;
import com.brightskies.biker_system.order.repository.OrderRepository;
import com.brightskies.biker_system.order.service.DeliveryAssignmentService;
import com.brightskies.biker_system.order.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BikerService {
    BikerRepository bikerRepository;
    DeliveryAssignmentService deliveryAssignmentService;
    OrderRepository orderRepository;
    OrderService orderService;


    @Autowired
    public BikerService(BikerRepository bikerRepository,
                        DeliveryAssignmentService deliveryAssignmentService,
                        OrderRepository orderRepository,
                        OrderService orderService) {
        this.bikerRepository = bikerRepository;
        this.deliveryAssignmentService = deliveryAssignmentService;
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    //handle validation for email, or phone to be not duplicate
    public void updateBiker(Long id, BikerDto bikerDTO) throws Exception {
        if(!bikerDTO.email().isEmpty() && bikerRepository.findByEmail(bikerDTO.email()) != null){
            throw new EmailExistsException();
        }
        Biker biker = bikerRepository.findById(id).orElseThrow(() -> new BikerNotFoundException(id));
        if(bikerDTO.email() != null)
            biker.setEmail(bikerDTO.email());
        if(bikerDTO.name() != null)
            biker.setName(bikerDTO.name());
        if(bikerDTO.phone() !=null)
            biker.setPhone(bikerDTO.phone());
        bikerRepository.save(biker);
    }

    //handle checking if id is already found or not
    public void deleteBiker(Long id){
        bikerRepository.deleteById(id);
    }

    public List<Order> getAllFreeOrders(){
        return orderService.getAllFreeOrders();
    }

    public DeliveryAssignment acceptOrder(Long orderId) throws Exception {
        return orderService.selectOrder(orderId);
    }


    public void updateAssignmentStatus(Long deliveryAssignmentId, AssignmentStatus status) throws Exception {
        deliveryAssignmentService.changeStatus(deliveryAssignmentId, status);
    }

    public List<OrderHistory> getCartItemsForUser(Long orderId){
        return orderService.getCartItemsForCurrentOrder(orderId);
    }

    public void deliverOrder(Long deliveryAssignmentId) throws Exception {
        Optional<DeliveryAssignment> deliveryAssignment = deliveryAssignmentService.getDeliveryAssignmentById(deliveryAssignmentId);
        if(deliveryAssignment.isEmpty()){
            throw new DeliveryAssignmentNotFoundException(SecurityUtils.getCurrentUserId());
        }
        updateAssignmentStatus(deliveryAssignment.get().getId(), AssignmentStatus.delivered);
        deliveryAssignmentService.setDeliveryTime(deliveryAssignment.get().getId(), LocalDate.now());
    }

    public double getBikerRating(){
        Biker biker = bikerRepository.findById(SecurityUtils.getCurrentUserId()).get();
        return biker.getRating();
    }

    public List<Order> getOrdersInZone() {
        Long bikerId = SecurityUtils.getCurrentUserId();
        Biker biker = bikerRepository.findById(bikerId).
                orElseThrow(() -> new EntityNotFoundException("biker not found"));
        return orderRepository.findAllFreeOrdersInZone(biker.getZone());
    }
}
