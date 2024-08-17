package com.brightskies.biker_system.repositories;

import com.brightskies.biker_system.models.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
