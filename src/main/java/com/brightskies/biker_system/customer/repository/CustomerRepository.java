package com.brightskies.biker_system.customer.repository;

import com.brightskies.biker_system.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "SELECT * FROM user where WHERE id = :customerId" , nativeQuery = true)
    Customer findByCustomerId(Long customerId);
}
