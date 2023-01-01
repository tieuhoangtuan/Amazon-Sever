package com.tuantieu.amazonserver.service.impl;

import com.tuantieu.amazonserver.entity.Role;
import com.tuantieu.amazonserver.repository.RoleRepository;
import com.tuantieu.amazonserver.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(String name){
        return roleRepository.findByName(name);
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findOneById(id);
    }
}
