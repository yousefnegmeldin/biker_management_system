package com.brightskies.biker_system.biker.model;

import com.brightskies.biker_system.biker.enums.BikerStatus;
import com.brightskies.biker_system.general.enums.UserRole;
import com.brightskies.biker_system.general.enums.Zone;
import com.brightskies.biker_system.general.models.User;
import com.brightskies.biker_system.order.model.Order;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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
    private double rating;

    @OneToMany(mappedBy = "biker", fetch = FetchType.EAGER)
    private List<Order> orders;

    public Biker(String name, String email, String phone, String password, LocalDate joinedAt, UserRole role, Zone zone, BikerStatus status) {
        super(name, email, phone, password, joinedAt, role);
        this.zone = zone;
        this.status = status;
        this.rating = 0;
    }
}
