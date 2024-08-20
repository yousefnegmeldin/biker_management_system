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
    public DeliveryAssignmentService(DeliveryAssignmentRepository deliveryAssignmentRepository, OrderRepository orderRepository) {
        this.deliveryAssignmentRepository = deliveryAssignmentRepository;
        this.orderRepository = orderRepository;
    }

    public DeliveryAssignmentDTO addDeliveryAssignment(DeliveryAssignmentDTO deliveryAssignmentDTO) throws Exception {
        if (orderRepository.existsById(deliveryAssignmentDTO.order())) {
            if (orderRepository.findFreeOrderById(deliveryAssignmentDTO.order()).isPresent()) {
                if(bikerRepository.findById(deliveryAssignmentDTO.biker()).isPresent()) {
                    orderRepository.assignBikerToOrder(deliveryAssignmentDTO.biker(), deliveryAssignmentDTO.order());
                    DeliveryAssignment deliveryAssignment = new DeliveryAssignment(
                            orderRepository.findById(deliveryAssignmentDTO.order()).get(),
                            LocalDate.now(),
                            bikerRepository.findById(deliveryAssignmentDTO.biker()).get(),
                            50L
                    );
                    deliveryAssignmentRepository.save(deliveryAssignment);
                    return new DeliveryAssignmentDTO(deliveryAssignment.getId(), deliveryAssignment.getBiker().getId(), deliveryAssignmentDTO.expectedTime());
                }
                else {
                    throw new InstanceNotFoundException("Biker with specified ID does not exist.");
                }
            }
            else {
                throw new IllegalArgumentException("Order has already been assigned a biker");
            }
        }
        else {
            throw new Exception("Order with the specified ID does not exist.");
        }
    }
    public void changeStatus(Long id, AssignmentStatus status) {
        if(deliveryAssignmentRepository.existsById(id)) {

        }
    }
}
