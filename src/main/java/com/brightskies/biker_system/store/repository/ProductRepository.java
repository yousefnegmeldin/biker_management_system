package com.brightskies.biker_system.store.repository;

import com.brightskies.biker_system.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM Product p WHERE p.id  =  :id")
    Product selectById(Long id);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    List<Product> findProductsByName(@Param("name") String name);

    void deleteByName(String name);
}

