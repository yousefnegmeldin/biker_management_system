package com.brightskies.biker_system.customer.controller;

import com.brightskies.biker_system.customer.dto.FeedbackDTO;
import com.brightskies.biker_system.generalservices.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private FeedbackService feedbackService;

    @Autowired
    public CustomerController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/feedback")
    public ResponseEntity<?> addFeedback(FeedbackDTO feedbackDTO) {
    }
}
