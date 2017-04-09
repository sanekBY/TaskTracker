package com.sashqua.tracker.repository;


import com.sashqua.tracker.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
}
