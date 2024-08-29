package com.brightskies.biker_system.feedback.dto;

import jakarta.validation.constraints.NotNull;

public record ViewFeedBackDTO(@NotNull Integer rating, String text) {
}
