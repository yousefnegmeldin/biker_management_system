package com.brightskies.biker_system.Authentication.dto;

import com.brightskies.biker_system.enums.UserRoleEnum;

public record RegisterUserDTO(String firstName,
                              String lastName,
                              String email,
                              String phone,
                              String password,
                              UserRoleEnum role) {
}
