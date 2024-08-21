package com.brightskies.biker_system.order.repository;

import com.brightskies.biker_system.generalmodels.User;
import com.brightskies.biker_system.order.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long>
{
    Optional<Cart> findByUser(User user);
}
