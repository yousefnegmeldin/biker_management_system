package com.brightskies.biker_system.authentication.dto;

import com.brightskies.biker_system.general.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record RegisterCustomerDTO (
        @NotNull
        String firstName,
        @NotNull
                                         String lastName,
                                         @Email
                                         String email,
                                         @NotNull
                                         String phone,
                                         @NotNull
                                         String password) {}
