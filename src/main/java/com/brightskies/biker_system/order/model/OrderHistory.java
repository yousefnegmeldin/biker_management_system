package com.brightskies.biker_system.order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderHistory
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long orderId;
    @Column(nullable = false)
    Long productId;
    @Column(nullable = false)
    int quantity;

}
