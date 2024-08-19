package com.brightskies.biker_system.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Date orderDate;

    @Column(nullable = false)
    private String orderStatus;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "biker_id",nullable = false)
    private Biker biker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer Customer;

}
