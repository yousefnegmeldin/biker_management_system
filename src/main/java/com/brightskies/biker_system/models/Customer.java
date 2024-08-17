package com.brightskies.biker_system.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Customer extends User {
    @Column
    private LocalDate lastLogin;
}