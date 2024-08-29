package com.brightskies.biker_system.customer.repository;

import com.brightskies.biker_system.customer.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    /*@Query("SELECT a.label FROM Address a WHERE a.customer.id = :customer")
    List<String> findAllLabelsByCustomer(@Param("customer") Long customer);

    @Modifying
    @Transactional
    @Query("DELETE FROM Address a WHERE a.customer.id = :customer AND a.label = :label")
    void deleteByCustomerAndLabel(@Param("customer") Long customer, @Param("label") String label);

    @Query("SELECT a FROM Address a WHERE a.customer.id = :customer AND a.label = :label")
    Optional<Address> findAddressByCustomerAndLabel(@Param("customer") Long customer, @Param("label") String label);*/
}
