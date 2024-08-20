package com.brightskies.biker_system.authentication.dto;

import com.brightskies.biker_system.enums.UserRoleEnum;

public record RegisterBikerDTO(String firstName,
                               String lastName,
                               String email,
                               String phone,
                               String password,
                               UserRoleEnum role
                               ) {
}
