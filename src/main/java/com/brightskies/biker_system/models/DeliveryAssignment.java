package com.brightskies.biker_system.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    private int expectedTime;

    @Column(nullable = false)
    private String status;
}
