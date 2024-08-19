package com.brightskies.biker_system.models;

import com.brightskies.biker_system.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Customer extends User {
    @Column
    private LocalDate lastLogin;

    public Customer(String name, String email, String phone, String password, LocalDate joinedAt, UserRole role, LocalDate lastLogin) {
        super(name, email, phone, password, joinedAt, role);
        this.lastLogin = lastLogin;
    }
}