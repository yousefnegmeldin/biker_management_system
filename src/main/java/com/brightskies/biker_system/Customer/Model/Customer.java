package com.brightskies.biker_system.Customer.Model;

import com.brightskies.biker_system.GeneralModels.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends User {
    @Column
    private LocalDate lastLogin;
}