package com.brightskies.biker_system.Customer.Repository;

import com.brightskies.biker_system.Customer.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
