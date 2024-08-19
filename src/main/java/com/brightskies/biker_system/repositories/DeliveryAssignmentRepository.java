package com.brightskies.biker_system.repositories;

import com.brightskies.biker_system.models.DeliveryAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryAssignmentRepository extends JpaRepository<DeliveryAssignment,Long> {
}
