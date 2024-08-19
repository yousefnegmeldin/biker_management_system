package com.brightskies.biker_system.store.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String area;
    @Column(nullable = false)
    private String status;

    public Store(String name,String area,String status){
        this.name = name;
        this.area = area;
        this.status = status;
    }
}
