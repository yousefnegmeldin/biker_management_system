package com.brightskies.biker_system.authentication.dto;

import com.brightskies.biker_system.enums.UserRoleEnum;

public record RegisterManagerDTO(String firstName,
                                 String lastName,
                                 String email,
                                 String phone,
                                 String password,
                                 UserRoleEnum role,
                                 String department
                                 ) {
}
