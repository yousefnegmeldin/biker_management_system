package com.brightskies.biker_system.Admin.Repository;

import com.brightskies.biker_system.Admin.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
