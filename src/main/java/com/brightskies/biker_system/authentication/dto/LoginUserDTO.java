package com.brightskies.biker_system.authentication.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginUserDTO(@NotBlank String email,@NotBlank String password) {
}
