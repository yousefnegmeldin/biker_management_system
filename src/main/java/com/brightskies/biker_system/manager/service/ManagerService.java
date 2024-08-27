package com.brightskies.biker_system.manager.service;

import com.brightskies.biker_system.biker.repository.BikerRepository;
import com.brightskies.biker_system.order.dto.DeliveryAssignmentDTO;
import com.brightskies.biker_system.order.service.DeliveryAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

    BikerRepository bikerRepository;
    DeliveryAssignmentService deliveryAssignmentService;
    @Autowired
    public ManagerService(BikerRepository bikerRepository,
                          DeliveryAssignmentService deliveryAssignmentService){
        this.bikerRepository = bikerRepository;
        this.deliveryAssignmentService = deliveryAssignmentService;
    }


    public void assignBikerToOrder(Long bikerId, Long orderId) throws Exception {
        // Assign the biker to the order
        deliveryAssignmentService.addDeliveryAssignment(new DeliveryAssignmentDTO(orderId,bikerId,30L));
    }
}
