package com.brightskies.biker_system.Order.Repository;

import com.brightskies.biker_system.Order.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,String>
{

}
