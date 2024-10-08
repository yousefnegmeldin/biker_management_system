package com.brightskies.biker_system.admin.dto;

import com.brightskies.biker_system.general.enums.UserRole;

import java.time.LocalDate;

public record AdminDto
        (
                String name,
                String email,
                String phone,
                String password,
                LocalDate joinedAt,
                UserRole role
        )
{
}
