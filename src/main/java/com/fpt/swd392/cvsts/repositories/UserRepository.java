package com.fpt.swd392.cvsts.repositories;

import com.fpt.swd392.cvsts.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, String>{
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    Optional<User> findById(String id);
}
