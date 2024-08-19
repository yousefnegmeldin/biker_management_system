package com.brightskies.biker_system.repositories;

import com.brightskies.biker_system.models.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct,String>
{
}
