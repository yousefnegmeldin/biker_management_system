package com.brightskies.biker_system.feedback.dto;

import com.brightskies.biker_system.feedback.model.FeedBack;

public class FeedBackMapper {
    public static FeedBackDTO toDTO (FeedBack feedback) {
        return new FeedBackDTO(
                feedback.getOrder().getId(),
                feedback.getRating(),
                feedback.getText()
        );
    }
}
