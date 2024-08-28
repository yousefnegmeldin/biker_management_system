package com.brightskies.biker_system.order.repository;

import com.brightskies.biker_system.order.model.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory,Long>
{

    List<OrderHistory> findByOrderId(Long orderId);
    @Modifying
    @Transactional
    @Query("DELETE FROM OrderHistory o WHERE o.order.id = :orderId")
    void deleteByOrderId(Long orderId);
}
