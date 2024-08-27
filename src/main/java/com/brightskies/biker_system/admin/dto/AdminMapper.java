package com.brightskies.biker_system.admin.dto;

import com.brightskies.biker_system.admin.model.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper
{
    public AdminDto toDTO(Admin admin)
    {
        return new AdminDto
                (
                        admin.getName(),
                        admin.getEmail(),
                        admin.getPhone(),
                        admin.getPassword(),
                        admin.getJoinedAt(),
                        admin.getRole()
                );
    }

    public Admin toEntity(AdminDto dto)
    {
        return new Admin
                (
                    dto.name(),
                    dto.phone(),
                    dto.email(),
                    dto.password(),
                    dto.joinedAt(),
                    dto.role()
                );
    }
}
