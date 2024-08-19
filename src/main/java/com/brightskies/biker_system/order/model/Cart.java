package com.brightskies.biker_system.order.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

@Entity
@Data
public class Cart
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long costumerId;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id",nullable = false)
    private String productId;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="store_id",nullable = false)
    private String storeId;
    @Column(nullable = false)
    private int quantity;
}
