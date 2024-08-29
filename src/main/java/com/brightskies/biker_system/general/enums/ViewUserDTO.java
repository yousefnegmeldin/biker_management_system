package com.brightskies.biker_system.general.enums;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record ViewUserDTO(@NotNull Long id, @NotNull String name, @Email String email, String phone, UserRole role) {
}
