package com.brightskies.biker_system.order.repository;

import com.brightskies.biker_system.general.enums.Zone;
import com.brightskies.biker_system.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("SELECT o FROM Order o WHERE o.id = :id AND o.biker IS NULL")
    Optional<Order> findFreeOrderById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.biker.id = :biker WHERE o.id = :id")
    void assignBikerToOrder(@Param("biker") Long biker, @Param("id") Long id);

    @Query("SELECT o FROM Order o WHERE o.biker IS NULL")
    List<Order> findAllFreeOrders();

    @Query("SELECT o FROM Order o WHERE o.biker IS NULL AND o.address.zone = :zone")
    List<Order> findAllFreeOrdersInZone(@Param("zone") Zone zone);
}
