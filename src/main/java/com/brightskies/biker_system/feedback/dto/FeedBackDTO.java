package com.brightskies.biker_system.feedback.dto;

import jakarta.validation.constraints.NotNull;

public record FeedBackDTO(@NotNull Long order, @NotNull Integer rating, String text) {
}
