package com.brightskies.biker_system.order.service;

import com.brightskies.biker_system.biker.model.Biker;
import com.brightskies.biker_system.biker.repository.BikerRepository;
import com.brightskies.biker_system.order.enums.AssignmentStatus;
import com.brightskies.biker_system.order.model.*;
import com.brightskies.biker_system.order.dto.DeliveryAssignmentDTO;
import com.brightskies.biker_system.order.repository.DeliveryAssignmentRepository;
import com.brightskies.biker_system.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import javax.management.InstanceNotFoundException;

@Service
public class DeliveryAssignmentService {
    private DeliveryAssignmentRepository deliveryAssignmentRepository;
    private OrderRepository orderRepository;
    private BikerRepository bikerRepository;

    @Autowired
    public DeliveryAssignmentService(DeliveryAssignmentRepository deliveryAssignmentRepository, OrderRepository orderRepository, BikerRepository bikerRepository) {
        this.deliveryAssignmentRepository = deliveryAssignmentRepository;
        this.orderRepository = orderRepository;
        this.bikerRepository = bikerRepository;
    }

    public DeliveryAssignment addDeliveryAssignment(DeliveryAssignmentDTO deliveryAssignmentDTO) throws Exception {
        Order order = orderRepository.findById(deliveryAssignmentDTO.order())
                .orElseThrow(() -> new Exception("Order with the specified ID does not exist."));
        if (orderRepository.findFreeOrderById(deliveryAssignmentDTO.order()).isEmpty()) {
            throw new IllegalArgumentException("Order has already been assigned a biker.");
        }
        Biker biker = bikerRepository.findById(deliveryAssignmentDTO.biker())
                .orElseThrow(() -> new InstanceNotFoundException("Biker with specified ID does not exist."));
        DeliveryAssignment deliveryAssignment = new DeliveryAssignment(
                order,
                LocalDate.now(),
                biker,
                50L
        );
        return deliveryAssignmentRepository.save(deliveryAssignment);
    }

    public void changeStatus(Long id, AssignmentStatus status) throws Exception {
        DeliveryAssignment deliveryAssignment = deliveryAssignmentRepository.findById(id)
                .orElseThrow(() -> new Exception("Order with the specified ID does not exist."));
        deliveryAssignment.setStatus(status);
        deliveryAssignmentRepository.save(deliveryAssignment);
    }

}
