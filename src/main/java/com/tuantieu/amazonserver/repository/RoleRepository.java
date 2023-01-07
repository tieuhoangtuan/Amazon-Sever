package com.tuantieu.amazonserver.repository;

import com.tuantieu.amazonserver.entity.Role;
import com.tuantieu.amazonserver.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    //4/1/2023
    Optional<Role> findByName(ERole name);

    Role findOneById(Long id);
}
