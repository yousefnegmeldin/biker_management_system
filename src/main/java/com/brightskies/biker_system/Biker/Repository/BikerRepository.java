package com.brightskies.biker_system.Biker.Repository;

import com.brightskies.biker_system.Biker.Model.Biker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikerRepository extends JpaRepository<Biker, Long> {
}
