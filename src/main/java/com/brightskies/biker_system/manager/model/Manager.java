package com.brightskies.biker_system.manager.model;

import com.brightskies.biker_system.generalmodels.User;
import com.brightskies.biker_system.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Manager extends User {
    @Column
    private String department;

    public Manager(String name, String email, String phone, String password, LocalDate joinedAt, UserRole role, String department) {
        super(name, email, phone, password, joinedAt, role);
        this.department = department;
    }
}
