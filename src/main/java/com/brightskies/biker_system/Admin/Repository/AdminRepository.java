package com.brightskies.biker_system.repositories;

import com.brightskies.biker_system.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
