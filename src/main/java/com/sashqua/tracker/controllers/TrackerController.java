package com.sashqua.tracker.controllers;

import com.sashqua.tracker.entitys.Project;
import com.sashqua.tracker.entitys.Task;
import com.sashqua.tracker.service.ProjectService;
import com.sashqua.tracker.service.TaskService;
import com.sashqua.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TrackerController {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;
    @Autowired
    private  ProjectService projectService;
    @Autowired
    private TaskService taskService;

    @RequestMapping("/api/user/info")
    public com.sashqua.tracker.entitys.User user() {
        com.sashqua.tracker.entitys.User user = userService.getUser(1);
        return user;
    }

    @RequestMapping(value = "/api/projects", method = RequestMethod.POST)
    public Project createProject (@RequestBody @Valid final Project project) {
        project.setTaskList(new ArrayList<>());
        return projectService.save(project);
    }

    @RequestMapping(value = "/api/projects", method = RequestMethod.GET)
    public List<Project> getUserProjects() {
        return projectService.getUserProjects(1);
    }

    @RequestMapping(value = "/api/project/{id}", method = {RequestMethod.GET})
    public Project getProject(@PathVariable("id") Integer id) {
        return projectService.getProject(id);
    }

    @RequestMapping(value = "/api/project/{id}", method = {RequestMethod.POST})
    public Project setUsersInProj(@PathVariable("id") Integer id, @RequestBody @Valid final List<com.sashqua.tracker.entitys.User> users) {
        return projectService.addUsers(id, users);
    }


    @RequestMapping(value = "/api/project/{id}/tasks", method = {RequestMethod.POST})
    public Task createTask(@PathVariable("id") Integer id, @RequestBody @Valid final Task task) {
        Project project = new Project();
        project = projectService.getProject(id);
        task.setProject(project);
        return taskService.save(task);
    }

    @RequestMapping(value = "/api/task/{id}", method = {RequestMethod.GET})
    public Task getTask(@PathVariable("id") Integer id) {
        return taskService.getTask(id);
    }

    @RequestMapping(value = "/api/users", method = {RequestMethod.GET})
    public List<com.sashqua.tracker.entitys.User> getUsers() {
        return userService.getAllDevelopers();
    }


//    @RequestMapping(value = "/api/project/{id}/task/{id}", method = {RequestMethod.GET})
//    public Project getVoter(@PathVariable("id") Integer id) {
//        return projectService.getProject(id);
//    }

}
