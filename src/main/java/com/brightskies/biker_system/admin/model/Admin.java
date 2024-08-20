package com.brightskies.biker_system.admin.model;

import com.brightskies.biker_system.generalmodels.User;
import com.brightskies.biker_system.generalmodels.UserRole;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Admin extends User {
    public Admin(String name, String email, String phone, String password, LocalDate joinedAt, UserRole role) {
        super(name, email, phone, password, joinedAt, role);
    }
}
