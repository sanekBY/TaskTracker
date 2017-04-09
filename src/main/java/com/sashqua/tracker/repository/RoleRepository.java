package com.sashqua.tracker.repository;


import com.sashqua.tracker.entitys.Role;
import com.sashqua.tracker.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Integer>{
}
