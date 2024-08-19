package com.brightskies.biker_system.biker.repository;

import com.brightskies.biker_system.biker.model.Biker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikerRepository extends JpaRepository<Biker, Long> {
}
