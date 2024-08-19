package com.brightskies.biker_system.store.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String description;
    @Column(nullable = false)
    String barcode;
    @Column(nullable = false)
    String category;
    @Column(nullable = false)
    double price;

    public Product(String name,String description,String barcode,String  category,double price){
        this.name = name;
        this.description = description;
        this.barcode = barcode;
        this.category = category;
        this.price = price;
    }

}
