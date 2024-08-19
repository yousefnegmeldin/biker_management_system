package com.brightskies.biker_system.models;

import com.brightskies.biker_system.enums.BikerStatus;
import com.brightskies.biker_system.enums.Zone;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Biker extends User {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Zone zone;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BikerStatus status;
    @Column
    private Integer rating;
}