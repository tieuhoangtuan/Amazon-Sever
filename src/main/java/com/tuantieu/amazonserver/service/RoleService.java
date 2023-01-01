package com.tuantieu.amazonserver.service;

import com.tuantieu.amazonserver.entity.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(String name);

    Role findById(Long id);
}
