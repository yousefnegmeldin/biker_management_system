package com.brightskies.biker_system.order.repository;

import com.brightskies.biker_system.order.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
