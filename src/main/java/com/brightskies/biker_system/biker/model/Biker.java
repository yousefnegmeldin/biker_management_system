package com.brightskies.biker_system.biker.model;

import com.brightskies.biker_system.biker.enums.BikerStatus;
import com.brightskies.biker_system.generalmodels.UserRole;
import com.brightskies.biker_system.biker.enums.Zone;
import com.brightskies.biker_system.generalmodels.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Biker extends User {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Zone zone;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BikerStatus status;
    @Column
    private Integer rating;

    public Biker(String name, String email, String phone, String password, LocalDate joinedAt, UserRole role, Zone zone, BikerStatus status) {
        super(name, email, phone, password, joinedAt, role);
        this.zone = zone;
        this.status = status;
        this.rating = 0;
    }
}