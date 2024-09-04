package com.brightskies.biker_system.authentication.dto;

import com.brightskies.biker_system.biker.enums.BikerStatus;
import com.brightskies.biker_system.general.enums.Zone;
import com.brightskies.biker_system.general.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterBikerDTO(
        @NotBlank
        String firstName,
        @NotNull
        String lastName,
        @Email
        String email,
        @NotNull
        String phone,
        @NotNull
        String password,
        @NotNull
        BikerStatus status,
        @NotNull
        Zone zone
                               ) {
}
