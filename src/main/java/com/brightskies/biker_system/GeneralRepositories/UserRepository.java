package com.brightskies.biker_system.GeneralRepositories;

import com.brightskies.biker_system.GeneralModels.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
