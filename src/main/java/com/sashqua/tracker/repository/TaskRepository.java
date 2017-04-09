package com.sashqua.tracker.repository;


import com.sashqua.tracker.entitys.Role;
import com.sashqua.tracker.entitys.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Integer>{
}
