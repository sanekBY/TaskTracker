package com.sashqua.tracker.repository;


import com.sashqua.tracker.entitys.Comment;
import com.sashqua.tracker.entitys.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProjectRepository extends JpaRepository<Project, Integer>{
    @Query("SELECT p FROM Project p where p.owner.id = ?1")
    List<Project> findUserProjects(Integer id);

}
