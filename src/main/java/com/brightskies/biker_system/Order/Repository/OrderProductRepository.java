package com.brightskies.biker_system.Order.Repository;

import com.brightskies.biker_system.Order.Model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct,String>
{
}
