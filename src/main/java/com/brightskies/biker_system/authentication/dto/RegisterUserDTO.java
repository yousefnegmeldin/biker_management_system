package com.brightskies.biker_system.authentication.dto;

import com.brightskies.biker_system.generalmodels.UserRole;

public record RegisterUserDTO(String firstName,
                              String lastName,
                              String email,
                              String phone,
                              String password,
                              UserRole role) {
}
