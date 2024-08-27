package com.brightskies.biker_system.biker.controller;

import com.brightskies.biker_system.feedback.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/biker")
public class BikerController {
    private FeedBackService feedBackService;

    @Autowired
    public BikerController(FeedBackService feedBackService) {
        this.feedBackService = feedBackService;
    }

    @GetMapping("/feedback/{biker}")
    public ResponseEntity<?> getFeedback(@PathVariable Long biker) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(feedBackService.allBikerFeedback(biker));
        }
        catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }
}
