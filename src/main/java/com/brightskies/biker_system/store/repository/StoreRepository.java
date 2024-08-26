package com.brightskies.biker_system.store.repository;

import com.brightskies.biker_system.store.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {
    Store findStoreByName(String name);
    @Query("SELECT s FROM Store s WHERE s.zone = :zone")
    List<Store> findStoresByZone(String zone);
}
