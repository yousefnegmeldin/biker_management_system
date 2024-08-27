package com.brightskies.biker_system.biker.service;

import com.brightskies.biker_system.authentication.utility.SecurityUtils;
import com.brightskies.biker_system.biker.dto.BikerDto;
import com.brightskies.biker_system.biker.model.Biker;
import com.brightskies.biker_system.biker.repository.BikerRepository;
import com.brightskies.biker_system.customer.model.Address;
import com.brightskies.biker_system.order.dto.DeliveryAssignmentDTO;
import com.brightskies.biker_system.order.enums.AssignmentStatus;
import com.brightskies.biker_system.order.model.CartItem;
import com.brightskies.biker_system.order.model.Order;
import com.brightskies.biker_system.order.service.DeliveryAssignmentService;
import com.brightskies.biker_system.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikerService {
    BikerRepository bikerRepository;
    DeliveryAssignmentService deliveryAssignmentService;
    OrderService orderService;


    @Autowired
    public BikerService(BikerRepository bikerRepository,
                        DeliveryAssignmentService deliveryAssignmentService,
                        OrderService orderService) {
        this.bikerRepository = bikerRepository;
        this.deliveryAssignmentService = deliveryAssignmentService;
    }

    public void updateBiker(Long id, BikerDto bikerDTO){
        Biker biker = bikerRepository.findById(id).orElseThrow(() -> new RuntimeException("Biker not found"));
        if(bikerDTO.email() != null)
            biker.setEmail(bikerDTO.email());
        if(bikerDTO.name() != null)
            biker.setName(bikerDTO.name());
        if(bikerDTO.phone() !=null)
            biker.setPhone(bikerDTO.phone());
        bikerRepository.save(biker);

    }

    public void deleteBiker(Long id){
        bikerRepository.deleteById(id);
    }

    public List<Order> getAllFreeOrders(){
        return orderService.getAllFreeOrders();
    }

    public void acceptOrder(Order order) throws Exception {
        orderService.selectOrder(order.getId());
    }


    public void updateAssignmentStatus(Long deliveryAssignmentId, AssignmentStatus status) throws Exception {
        deliveryAssignmentService.changeStatus(deliveryAssignmentId, status);
    }

    public List<CartItem> getCartItemsForUser(Long orderId){
        return orderService.getCartItemsForCurrentOrder(orderId);
    }

    public void deliverOrder() throws Exception {
        updateAssignmentStatus(SecurityUtils.getCurrentUserId(), AssignmentStatus.delivered);
    }


}
