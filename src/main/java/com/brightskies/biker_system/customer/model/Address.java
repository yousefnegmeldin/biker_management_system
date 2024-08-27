package com.brightskies.biker_system.customer.model;

import com.brightskies.biker_system.general.enums.Zone;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    @Column(unique = true, nullable = false)
    private String label;
    @Column(nullable = false)
    private Zone zone;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String apartment;

    public Address(Customer customer, String label, Zone zone, String city, String street, String apartment) {
        this.customer = customer;
        this.label = label;
        this.zone = zone;
        this.city = city;
        this.street = street;
        this.apartment = apartment;
    }
}