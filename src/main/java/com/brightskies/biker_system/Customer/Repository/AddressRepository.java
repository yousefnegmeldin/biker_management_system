package com.brightskies.biker_system.Customer.Repository;

import com.brightskies.biker_system.Customer.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
