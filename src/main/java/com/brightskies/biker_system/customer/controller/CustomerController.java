package com.brightskies.biker_system.customer.controller;

import com.brightskies.biker_system.feedback.dto.FeedBackDTO;
import com.brightskies.biker_system.feedback.dto.FeedBackMapper;
import com.brightskies.biker_system.feedback.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private FeedBackService feedbackService;

    @Autowired
    public CustomerController(FeedBackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/feedback")
    public ResponseEntity<?> addFeedback(@RequestBody FeedBackDTO feedbackDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                FeedBackMapper.toDTO(
                        feedbackService.addFeedback(feedbackDTO)
                )
        );
    }
}
