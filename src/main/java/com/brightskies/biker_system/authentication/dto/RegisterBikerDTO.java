package com.brightskies.biker_system.authentication.dto;

import com.brightskies.biker_system.biker.enums.BikerStatus;
import com.brightskies.biker_system.general.enums.Zone;
import com.brightskies.biker_system.general.enums.UserRole;

public record RegisterBikerDTO(String firstName,
                               String lastName,
                               String email,
                               String phone,
                               String password,
                               UserRole role,
                               BikerStatus status,
                               Zone zone
                               ) {
}
