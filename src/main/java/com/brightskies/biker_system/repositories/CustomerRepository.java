package com.brightskies.biker_system.repositories;

import com.brightskies.biker_system.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
