package com.brightskies.biker_system.order.repository;

import com.brightskies.biker_system.order.model.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderHistory,String>
{
}
