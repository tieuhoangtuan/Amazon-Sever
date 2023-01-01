package com.tuantieu.amazonserver.service;

import com.tuantieu.amazonserver.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String name);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User insertUser(User user);
}
