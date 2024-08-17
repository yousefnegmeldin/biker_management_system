package com.brightskies.biker_system.models;

import com.brightskies.biker_system.enums.UserRole;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String password;
    @Column
    private LocalDate joinedAt;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;
}

