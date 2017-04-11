package com.sashqua.tracker.service;

import com.sashqua.tracker.entitys.Comment;
import com.sashqua.tracker.entitys.Project;
import com.sashqua.tracker.entitys.User;

import java.util.List;
import java.util.Set;

public interface ProjectService {
    Project save(Project proj);
    Project getProject(Integer id);
    List<Project> getUserProjects(Integer userId);
    List<Project> getDeveloperProjects(Integer userId);
    List<Project> getDeveloperProjects(User user);
    Project addUsers(Integer projectId, Set<User> users);
}
