package com.sashqua.tracker.service;

import com.sashqua.tracker.entitys.Project;
import com.sashqua.tracker.entitys.Role;

import java.util.List;

public interface RoleService {
    Role save(Role role);
    Role getRole(Integer id);
    List<Role> findAll();
}
