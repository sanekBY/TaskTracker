package com.sashqua.tracker.service;

import com.sashqua.tracker.entitys.Comment;
import com.sashqua.tracker.entitys.Project;

import java.util.List;

public interface ProjectService {
    Project save(Project proj);
    Project getProject(Integer id);
    List<Project> getUserProjects(Integer userId);
}
