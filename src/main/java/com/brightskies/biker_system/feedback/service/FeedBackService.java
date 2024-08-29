package com.brightskies.biker_system.feedback.service;

import com.brightskies.biker_system.biker.model.Biker;
import com.brightskies.biker_system.biker.repository.BikerRepository;
import com.brightskies.biker_system.exception.model.BikerNotFoundException;
import com.brightskies.biker_system.exception.model.DeliveryAssignmentException;
import com.brightskies.biker_system.exception.model.OrderNotFoundException;
import com.brightskies.biker_system.feedback.dto.FeedBackDTO;
import com.brightskies.biker_system.feedback.dto.ViewFeedBackDTO;
import com.brightskies.biker_system.feedback.model.FeedBack;
import com.brightskies.biker_system.feedback.repository.FeedBackRepository;
import com.brightskies.biker_system.order.enums.AssignmentStatus;
import com.brightskies.biker_system.order.model.DeliveryAssignment;
import com.brightskies.biker_system.order.model.Order;
import com.brightskies.biker_system.order.repository.DeliveryAssignmentRepository;
import com.brightskies.biker_system.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedBackService {
    private FeedBackRepository feedbackRepository;
    private OrderRepository orderRepository;
    private DeliveryAssignmentRepository deliveryAssignmentRepository;
    private BikerRepository bikerRepository;

    @Autowired
    public FeedBackService(FeedBackRepository feedbackRepository, OrderRepository orderRepository, DeliveryAssignmentRepository deliveryAssignmentRepository, BikerRepository bikerRepository) {
        this.feedbackRepository = feedbackRepository;
        this.orderRepository = orderRepository;
        this.deliveryAssignmentRepository = deliveryAssignmentRepository;
        this.bikerRepository = bikerRepository;
    }

    public void updateRating(Biker biker, Integer rating) {
        long ratingsCount = feedbackRepository.countFeedbackByBiker(biker.getId());
        double currentAverage = biker.getRating();
        double newAverage = ((currentAverage * ratingsCount) + rating)/(ratingsCount + 1);
        biker.setRating(newAverage);
        bikerRepository.save(biker);
    }

    public FeedBack addFeedback(FeedBackDTO feedbackDTO) {
        Order order = orderRepository.findById(feedbackDTO.order())
                .orElseThrow(() -> new OrderNotFoundException(feedbackDTO.order()));
        DeliveryAssignment deliveryAssignment = deliveryAssignmentRepository.findById(feedbackDTO.order())
                .orElseThrow(() -> new DeliveryAssignmentException("Order has not been assigned a biker yet, nothing to rate."));
        if(!(deliveryAssignment.getStatus() == AssignmentStatus.delivered)) {
            throw new DeliveryAssignmentException("Order has not been delivered yet, nothing to rate.");
        }
        if(feedbackDTO.rating() < 0 || feedbackDTO.rating() > 5) {
            throw new IllegalArgumentException("Rating is out of range.");
        }
        FeedBack feedback = new FeedBack(order, feedbackDTO.rating(), feedbackDTO.text());
        updateRating(order.getBiker(), feedbackDTO.rating());
        return feedbackRepository.save(feedback);
    }

    public List<ViewFeedBackDTO> allBikerFeedback(Long id) {
        if(bikerRepository.findById(id).isEmpty()) {
            throw new BikerNotFoundException(id);
        }
        List<Object[]> feedbackList = feedbackRepository.findRatingAndTextByBikerId(id);
        return feedbackList.stream()
                .map(feedback -> new ViewFeedBackDTO((Integer) feedback[0], (String) feedback[1]))
                .collect(Collectors.toList());
    }
}
