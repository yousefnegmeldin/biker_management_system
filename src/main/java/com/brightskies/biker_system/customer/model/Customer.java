package com.brightskies.biker_system.customer.model;

import com.brightskies.biker_system.generalmodels.User;
import com.brightskies.biker_system.enums.UserRoleEnum;
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

    public Customer(String name, String email, String phone, String password, LocalDate joinedAt, UserRoleEnum role, LocalDate lastLogin) {
        super(name, email, phone, password, joinedAt, role);
        this.lastLogin = lastLogin;
    }
}