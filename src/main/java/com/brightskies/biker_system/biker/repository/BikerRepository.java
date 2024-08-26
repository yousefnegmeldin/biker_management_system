package com.brightskies.biker_system.biker.repository;

import com.brightskies.biker_system.biker.model.Biker;
import com.brightskies.biker_system.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikerRepository extends JpaRepository<Biker, Long> {
    @Query("SELECT b FROM Biker b WHERE b.name LIKE %:name%")
    List<Biker> findBikersByName(String name);
    @Query("SELECT b FROM Biker b WHERE b.status = :status")
    List<Biker> findBikersByStatus(String status);
    @Query("SELECT b FROM Biker b WHERE b.zone = :status")
    List<Biker> findBikersByZone(String zone);

    Biker findByPhone(String phone);
}
