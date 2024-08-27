package com.brightskies.biker_system.generalservices;

import com.brightskies.biker_system.biker.model.Biker;
import com.brightskies.biker_system.biker.repository.BikerRepository;
import com.brightskies.biker_system.customer.dto.FeedbackDTO;
import com.brightskies.biker_system.customer.model.Address;
import com.brightskies.biker_system.generalmodels.FeedBack;
import com.brightskies.biker_system.generalrepositories.FeedbackRepository;
import com.brightskies.biker_system.order.model.Order;
import com.brightskies.biker_system.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.ParameterOutOfBoundsException;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
    private FeedbackRepository feedbackRepository;
    private OrderRepository orderRepository;
    private BikerRepository bikerRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository, OrderRepository orderRepository, BikerRepository bikerRepository) {
        this.feedbackRepository = feedbackRepository;
        this.orderRepository = orderRepository;
        this.bikerRepository = bikerRepository;
    }

    public FeedBack addFeedback(FeedbackDTO feedbackDTO) throws Exception {
        Order order = orderRepository.findById(feedbackDTO.order())
                .orElseThrow(() -> new Exception("Order instance does not exist."));
        if(feedbackDTO.rating() < 0 || feedbackDTO.rating() > 5) {
            throw new IllegalArgumentException("Rating is out of range.");
        }
        FeedBack feedback = new FeedBack(order, feedbackDTO.rating(), feedbackDTO.text());
        //to calculate new biker rating
        Biker biker = order.getBiker();
        long ratingsCount = feedbackRepository.countFeedbackByBiker(biker.getId());
        double currentAverage = biker.getRating();
        double newAverage = ((currentAverage * ratingsCount) + feedbackDTO.rating())/(ratingsCount + 1);
        biker.setRating(newAverage);
        bikerRepository.save(biker);
        return feedbackRepository.save(feedback);
    }
}
