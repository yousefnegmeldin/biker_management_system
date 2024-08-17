package com.brightskies.biker_system.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Manager extends User{
    @Column
    private String department;
}
