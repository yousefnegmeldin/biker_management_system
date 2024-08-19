package com.brightskies.biker_system.repositories;

import com.brightskies.biker_system.models.Biker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikerRepository extends JpaRepository<Biker, Long> {
}
