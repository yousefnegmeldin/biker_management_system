package com.brightskies.biker_system.Biker.Model;

import com.brightskies.biker_system.enums.BikerStatus;
import com.brightskies.biker_system.enums.Zone;
import com.brightskies.biker_system.GeneralModels.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
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
}
