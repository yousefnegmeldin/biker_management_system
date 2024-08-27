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
import java.time.LocalTime;

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

    public void setDeliveryTime(Long deliveryAssignmentId,LocalDate date){
        DeliveryAssignment deliveryAssignment = deliveryAssignmentRepository.findById(deliveryAssignmentId).get();
        deliveryAssignment.setDeliveredAt(date);
        deliveryAssignmentRepository.save(deliveryAssignment);
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
        order.setBiker(biker);
        orderRepository.save(order);
        return deliveryAssignmentRepository.save(deliveryAssignment);
    }


    //have to check for safety that the delivery assignment id is assigned to same biker id when
    //they change
    public void changeStatus(Long id, AssignmentStatus status) throws Exception {
        DeliveryAssignment deliveryAssignment = deliveryAssignmentRepository.findById(id)
                .orElseThrow(() -> new Exception("Order with the specified ID does not exist."));
        if(deliveryAssignment.getStatus() == status) {
            throw new IllegalArgumentException("Delivery assignment already has the requested status as the current one.");
        }
        deliveryAssignment.setStatus(status);
        deliveryAssignmentRepository.save(deliveryAssignment);
    }

}
