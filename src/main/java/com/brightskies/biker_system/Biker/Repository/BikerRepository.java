package com.brightskies.biker_system.Biker.Repository;

import com.brightskies.biker_system.Biker.Model.Biker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikerRepository extends JpaRepository<Biker, Long> {
}
