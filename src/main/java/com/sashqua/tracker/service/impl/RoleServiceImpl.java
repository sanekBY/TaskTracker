package com.sashqua.tracker.service.impl;

import com.sashqua.tracker.entitys.Role;
import com.sashqua.tracker.repository.RoleRepository;
import com.sashqua.tracker.repository.UserRepository;
import com.sashqua.tracker.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Service("roleService")
@Validated
public class RoleServiceImpl implements RoleService {
    @Autowired
    private final RoleRepository roleRepository;


    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getRole(Integer id) {
        return roleRepository.findOne(id);
    }

    @Override
    public List<Role> findAll() {
        List<Role> list = new ArrayList<>();
        list.addAll(roleRepository.findAll());
        return list;
    }
}
