package com.brightskies.biker_system.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Entity
@Data
public class Cart
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long costumerId;

    @Column(nullable = false)
    String productId;
    @Column(nullable = false)
    String storeId;
    @Column(nullable = false)
    int quantity;
}
