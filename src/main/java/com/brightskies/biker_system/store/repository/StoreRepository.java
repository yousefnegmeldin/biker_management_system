package com.brightskies.biker_system.store.repository;

import com.brightskies.biker_system.store.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store,Long>
{
    public Store findbyId(Long id);
    List<Store> findByArea(String area);
    List<Store> findByStatus(boolean status);
}
