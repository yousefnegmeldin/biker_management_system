package com.brightskies.biker_system.customer.repository;

import com.brightskies.biker_system.customer.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
