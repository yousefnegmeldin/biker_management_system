package com.brightskies.biker_system.Authentication.dto;

import com.brightskies.biker_system.enums.UserRole;

public record RegisterUserDTO(String firstName,
                              String lastName,
                              String email,
                              String phone,
                              String password,
                              UserRole role) {
}
