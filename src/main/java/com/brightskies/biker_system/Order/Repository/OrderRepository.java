package com.brightskies.biker_system.repositories;
import com.brightskies.biker_system.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
