package com.brightskies.biker_system.order.model;

import com.brightskies.biker_system.biker.model.Biker;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeliveryAssignment {

    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private Orderr orderr;

    @Column(nullable = false)
    private Date assignedAt;

    @Column(nullable = false)
    private Date deliveredAt;

    @OneToOne
    @JoinColumn(name = "biker_id" , nullable = false)
    private Biker biker;

    @Column(nullable = false)
    private Long expectedTime;

    @Column(nullable = false)
    private String status;
}
