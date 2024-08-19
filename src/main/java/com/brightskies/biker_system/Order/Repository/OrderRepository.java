package com.brightskies.biker_system.Order.Repository;
import com.brightskies.biker_system.Order.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
