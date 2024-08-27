package com.brightskies.biker_system.authentication.dto;

import com.brightskies.biker_system.general.enums.UserRole;

public record RegisterUserDTO(String firstName,
                              String lastName,
                              String email,
                              String phone,
                              String password,
                              UserRole role) {
}
