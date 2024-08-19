package com.brightskies.biker_system.Manager.Model;

import com.brightskies.biker_system.GeneralModels.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manager extends User {
    @Column
    private String department;
}
