package com.brightskies.biker_system.Manager.Repository;

import com.brightskies.biker_system.Manager.Model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
