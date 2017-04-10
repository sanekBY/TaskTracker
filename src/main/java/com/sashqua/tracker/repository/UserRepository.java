package com.sashqua.tracker.repository;


import com.sashqua.tracker.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer>{

    @Query("SELECT u FROM User u where u.email = ?1")
    User findByEmail(String email);

    @Query("SELECT u FROM User u where u.role.id = ?1")
    List<User> findByRole(Integer id);
//    "from User u where u.login = :login"
}
