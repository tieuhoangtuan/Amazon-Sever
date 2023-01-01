package com.tuantieu.amazonserver.service.impl;

import com.tuantieu.amazonserver.entity.User;
import com.tuantieu.amazonserver.repository.UserRepository;
import com.tuantieu.amazonserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    @Override
    public User insertUser(User user){
        return userRepository.save(user);
    }
}
