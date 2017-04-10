package com.sashqua.tracker.service;

import com.sashqua.tracker.entitys.Comment;
import com.sashqua.tracker.entitys.Project;
import com.sashqua.tracker.entitys.User;

import java.util.List;

public interface ProjectService {
    Project save(Project proj);
    Project getProject(Integer id);
    List<Project> getUserProjects(Integer userId);
    Project addUsers(Integer projectId, List<User> users);
}
