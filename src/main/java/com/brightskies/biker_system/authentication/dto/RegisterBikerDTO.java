package com.brightskies.biker_system.authentication.dto;

import com.brightskies.biker_system.biker.enums.BikerStatus;
import com.brightskies.biker_system.biker.enums.Zone;
import com.brightskies.biker_system.generalmodels.UserRole;

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
