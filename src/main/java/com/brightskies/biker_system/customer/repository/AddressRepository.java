package com.brightskies.biker_system.customer.repository;

import com.brightskies.biker_system.customer.model.Address;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT a.label FROM Address a WHERE a.customer.id = :customer")
    List<String> findAllLabelsByCustomer(@Param("customer") Long customer);

    @Modifying
    @Transactional
    @Query("DELETE FROM Address a WHERE a.customer.id = :customer AND a.label = :label")
    void deleteByCustomerAndLabel(@Param("customer") Long customer, @Param("label") String label);

    @Query("SELECT a FROM Address a WHERE a.customer.id = :customer AND a.label = :label")
    Optional<Address> findAddressByCustomerAndLabel(@Param("customer") Long customer, @Param("label") String label);
}
