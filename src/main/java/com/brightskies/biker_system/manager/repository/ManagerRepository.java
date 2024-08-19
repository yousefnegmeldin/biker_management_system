package com.brightskies.biker_system.manager.repository;

import com.brightskies.biker_system.manager.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
