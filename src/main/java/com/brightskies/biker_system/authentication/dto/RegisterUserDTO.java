package com.brightskies.biker_system.authentication.dto;

import com.brightskies.biker_system.general.enums.UserRole;
import jakarta.validation.constraints.NotNull;

public record RegisterUserDTO(
        @NotNull              String firstName,
        @NotNull              String lastName,
        @NotNull              String email,
        @NotNull              String phone,
        @NotNull              String password,
        @NotNull              UserRole role) {
}
