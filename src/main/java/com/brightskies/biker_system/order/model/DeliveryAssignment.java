package com.brightskies.biker_system.order.model;

import com.brightskies.biker_system.biker.model.Biker;
import com.brightskies.biker_system.order.enums.AssignmentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
    private Order order;

    @Column(nullable = false)
    private LocalDate assignedAt;

    @Column
    private LocalDate deliveredAt;

    @ManyToOne
    @JoinColumn(name = "biker_id" , nullable = false)
    private Biker biker;

    @Column(nullable = false)
    private Long expectedTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AssignmentStatus status;

    public DeliveryAssignment(Order order, LocalDate assignedAt, Biker biker, Long expectedTime) {
        this.order = order;
        this.assignedAt = assignedAt;
        this.deliveredAt = null;
        this.biker = biker;
        this.expectedTime = expectedTime;
        this.status = AssignmentStatus.pending;
    }
}
