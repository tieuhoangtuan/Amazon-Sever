package com.tuantieu.amazonserver.service;

import com.tuantieu.amazonserver.entity.Role;
import com.tuantieu.amazonserver.enums.ERole;

import java.util.Optional;

public interface RoleService {
    //4/1/2023
    Optional<Role> findByName(ERole name);

    Role findById(Long id);
}
