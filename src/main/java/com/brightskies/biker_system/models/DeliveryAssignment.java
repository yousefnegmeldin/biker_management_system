package com.brightskies.biker_system.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeliveryAssignment {

    @Id
    @OneToOne
    @JoinColumn(name = "id")
    private Order order;

    @Column(nullable = false)
    private Date assignedAt;

    @Column(nullable = false)
    private Date deliveredAt;

    @OneToOne
    @JoinColumn(name = "biker_id" , nullable = false)
    private Biker biker;

    @Column(nullable = false)
    private long expectedTime;

    @Column(nullable = false)
    private String status;
}
