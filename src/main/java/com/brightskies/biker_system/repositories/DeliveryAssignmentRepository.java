package com.brighskies.biker_system.repositories;

import com.brighskies.biker_system.models.DeliveryAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryAssignmentRepository extends JpaRepository<DeliveryAssignment,Integer> {
}
