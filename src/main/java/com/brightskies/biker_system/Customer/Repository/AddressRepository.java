package com.brightskies.biker_system.repositories;

import com.brightskies.biker_system.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
