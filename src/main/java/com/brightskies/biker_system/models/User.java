package com.brightskies.biker_system.models;

import com.brightskies.biker_system.enums.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@MappedSuperclass
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String phone;
    @Column(nullable = false)
    private String password;
    @Column
    private LocalDate joinedAt;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User(String name, String email, String phone, String password, LocalDate joinedAt, UserRole role) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.joinedAt = joinedAt;
        this.role = role;
    }
}

