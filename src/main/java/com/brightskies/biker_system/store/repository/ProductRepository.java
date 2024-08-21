package com.brightskies.biker_system.store.repository;

import com.brightskies.biker_system.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>
{
    List<Product> findAllByname(String name);
    void deleteByname(String name);
    List<Product> findAllByCategory(String category);

}
