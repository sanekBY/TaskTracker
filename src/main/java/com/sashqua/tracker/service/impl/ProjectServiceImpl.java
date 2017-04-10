package com.sashqua.tracker.service.impl;

import com.sashqua.tracker.entitys.Project;
import com.sashqua.tracker.entitys.Role;
import com.sashqua.tracker.entitys.User;
import com.sashqua.tracker.repository.ProjectRepository;
import com.sashqua.tracker.repository.RoleRepository;
import com.sashqua.tracker.service.ProjectService;
import com.sashqua.tracker.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service("projectService")
@Validated
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    @Override
    @Transactional
    public Project save(@NotNull @Valid final Project proj) {
       return projectRepository.save(proj);
    }

    @Override
    public Project getProject(Integer id) {
        return projectRepository.findOne(id);
    }

    @Override
    public List<Project> getUserProjects(Integer userId) {
        return projectRepository.findUserProjects(userId);
    }

    @Override
    public Project addUsers(Integer projectId, List<User> users) {
        Project proj = projectRepository.findOne(projectId);
        List<User> userList = proj.getUsers();
        userList.addAll(users);
        proj.setUsers(userList);
        return projectRepository.save(proj);
    }

}
