package com.brightskies.biker_system.general.repositories;

import com.brightskies.biker_system.general.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);


    @Query("SELECT u FROM User u WHERE u.name LIKE %:name%")
    List<User> findUsersByName(@Param("name") String name);
}
