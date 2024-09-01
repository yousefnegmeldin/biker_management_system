package com.brightskies.biker_system.authentication.dto;

import jakarta.validation.constraints.NotNull;

public record UserDTO(@NotNull String name,@NotNull String email) {
}
