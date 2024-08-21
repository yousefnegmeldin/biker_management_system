package com.brightskies.biker_system.order.repository;

import com.brightskies.biker_system.order.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartItem,Long>
{
    @Query("SELECT c FROM CartItem c WHERE c.customer.id = :currentUserId")
    List<CartItem> findAllCartItems(Long currentUserId);

    @Query("DELETE FROM CartItem c WHERE c.id = :cartItemId")
    void deleteSpecific(Long cartItemId);

    @Query ("SELECT c FROM CartItem c WHERE c.customer.id = :currentUserId AND c.product.id = :prodId")
    CartItem findSpecific (Long currentUserId, Long prodId);
}
