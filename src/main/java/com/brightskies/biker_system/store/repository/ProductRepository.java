package com.brightskies.biker_system.store.repository;

import com.brightskies.biker_system.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p FROM Product p WHERE p.id  =  :id")
    public Product selectById(Long id);
}
