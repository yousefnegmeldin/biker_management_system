package com.brightskies.biker_system.order.repository;

import com.brightskies.biker_system.order.model.DeliveryAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryAssignmentRepository extends JpaRepository<DeliveryAssignment,Long> {

}
