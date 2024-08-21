package com.brightskies.biker_system.customer.repository;

import com.brightskies.biker_system.customer.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT a.label FROM Address a WHERE a.customer = :customer")
    List<String> findAllProductsInCartByCustomerId(@Param("customer") Long customer);

    @Modifying
    @Transactional
    @Query("DELETE FROM Address a WHERE a.customer = :customer AND a.label = :label")
    void deleteByCustomerAndLabel(@Param("customer") Long customer, @Param("label") String label);

    @Modifying
    @Transactional
    @Query("UPDATE Address a SET a.street = :street, a.city = :city, a.apartment = :apartment WHERE a.customer = :customer")
    void updateAddressByCustomer(@Param("street") String street, @Param("city") String city, @Param("apartment") String apartment, @Param("customer") Long customer);
}
