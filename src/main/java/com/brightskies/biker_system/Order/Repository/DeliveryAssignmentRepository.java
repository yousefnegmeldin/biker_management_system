package com.brightskies.biker_system.Order.Repository;

import com.brightskies.biker_system.Order.Model.DeliveryAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryAssignmentRepository extends JpaRepository<DeliveryAssignment,Long> {
}
