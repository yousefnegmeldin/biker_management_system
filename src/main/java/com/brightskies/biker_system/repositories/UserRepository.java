package com.brightskies.biker_system.repositories;

import com.brightskies.biker_system.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
