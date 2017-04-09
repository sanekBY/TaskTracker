package com.sashqua.tracker.repository;


import com.sashqua.tracker.entitys.Project;
import com.sashqua.tracker.entitys.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskStatusRepository extends JpaRepository<TaskStatus, Integer>{
}
