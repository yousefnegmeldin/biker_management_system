package com.brightskies.biker_system.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Entity
@Data
public class Cart
{

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customerId")
    private Customer customer;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="productId",nullable = false)
    private Product product;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="storeId",nullable = false)
    private Store store;
    @Column(nullable = false)
    private int quantity;

    public Cart(){}

    public Cart(Customer customer, Product product, Store store, int quantity) {
        this.customer = customer;
        this.product=product;
        this.store=store;
        this.quantity = quantity;
    }

    
}
