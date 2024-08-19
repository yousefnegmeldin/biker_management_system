package com.brightskies.biker_system.repositories;

import com.brightskies.biker_system.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,String>
{

}
