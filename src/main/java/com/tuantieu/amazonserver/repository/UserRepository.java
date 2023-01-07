package com.tuantieu.amazonserver.repository;

import com.tuantieu.amazonserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findOneById(Long id);
    Optional<User> findByUsername(String name);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
