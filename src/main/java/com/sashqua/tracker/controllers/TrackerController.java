package com.sashqua.tracker.controllers;

import com.sashqua.tracker.entitys.Comment;
import com.sashqua.tracker.entitys.Project;
import com.sashqua.tracker.entitys.Role;
import com.sashqua.tracker.entitys.Task;
import com.sashqua.tracker.service.CommentService;
import com.sashqua.tracker.service.ProjectService;
import com.sashqua.tracker.service.TaskService;
import com.sashqua.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.util.Set;

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
    @Autowired
    private CommentService commentService;

    @RequestMapping("/api/user/info")
    public com.sashqua.tracker.entitys.User user() {
        com.sashqua.tracker.entitys.User user = userService.getUser(1);
        return user;
    }

    @RequestMapping(value = "/api/projects", method = RequestMethod.POST)
    public Project createProject (@RequestBody @Valid final Project project) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        com.sashqua.tracker.entitys.User user = userService.findByLogin(auth.getName());
        project.setTaskList(new ArrayList<>());
        project.setOwner(user);
        return projectService.save(project);
    }

    @RequestMapping(value = "/api/projects", method = RequestMethod.GET)
    public List<Project> getUserProjects() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        com.sashqua.tracker.entitys.User user = userService.findByLogin(auth.getName());
        if (user.getRole().getId() == Role.MANAGER) {
            return projectService.getUserProjects(user.getId());
        } else if (user.getRole().getId() == Role.DEVELOPER) {
            List<Project> projects = new ArrayList<>();
            projects = projectService.getDeveloperProjects(user.getId());
            return projects;
        }
        return null;
    }

    @RequestMapping(value = "/api/project/{id}", method = {RequestMethod.GET})
    public Project getProject(@PathVariable("id") Integer id) {
        return projectService.getProject(id);
    }

    @RequestMapping(value = "/api/project/{id}", method = {RequestMethod.POST})
    public Project setUsersInProj(@PathVariable("id") Integer id, @RequestBody @Valid final Set<com.sashqua.tracker.entitys.User> users) {
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

    @RequestMapping(value = "/api/task/{id}", method = {RequestMethod.POST})
    public Task setUsersInTask(@PathVariable("id") Integer id, @RequestBody @Valid final Set<com.sashqua.tracker.entitys.User> users) {
        return taskService.addUsers(id, users);
    }

    @RequestMapping(value = "/api/task/{id}/comment", method = {RequestMethod.POST})
    public Comment setCommentInTask(@PathVariable("id") Integer id, @RequestBody @Valid final Comment comment) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        com.sashqua.tracker.entitys.User user = userService.findByLogin(auth.getName());
        comment.setTask(taskService.getTask(id));
        comment.setOwner(user);
        return commentService.addComment(comment);
    }


    @RequestMapping(value = "/api/users", method = {RequestMethod.GET})
    public List<com.sashqua.tracker.entitys.User> getUsers() {
        return userService.getAllDevelopers();
    }

    @RequestMapping(value = "/api/comment/{id}", method = {RequestMethod.GET})
    public Comment getComment(@PathVariable("id") Integer id) {
        return commentService.getComment(id);
    }

//    @RequestMapping(value = "/api/statuses", method = {RequestMethod.GET})
//    public List<com.sashqua.tracker.entitys.User> getUsers() {
//
//        return userService.getAllDevelopers();
//    }


}
