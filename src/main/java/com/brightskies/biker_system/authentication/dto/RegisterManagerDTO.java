package com.brightskies.biker_system.authentication.dto;

import com.brightskies.biker_system.general.enums.UserRole;

public record RegisterManagerDTO(String firstName,
                                 String lastName,
                                 String email,
                                 String phone,
                                 String password,
                                 UserRole role,
                                 String department
                                 ) {
}
