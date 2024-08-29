package com.brightskies.biker_system.authentication.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginResponseDTO(@NotBlank String token,@NotBlank long expiresIn) {
}
