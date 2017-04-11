package com.sashqua.tracker.service.impl;

import com.sashqua.tracker.entitys.Task;
import com.sashqua.tracker.entitys.User;
import com.sashqua.tracker.repository.TaskRepository;
import com.sashqua.tracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Set;

@Service("taskService")
@Validated
public class TaskServiceImpl implements TaskService {
    @Autowired
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {

        this.taskRepository = taskRepository;
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task getTask(Integer id) {
        return taskRepository.findOne(id);
    }

    @Override
    public Task addUsers(Integer id, Set<User> users) {
        Task task = getTask(id);
        Set<User> userList = task.getUserList();
        userList.addAll(users);
        if (userList.size() != users.size()) {
            task.setUserList(userList);
            return taskRepository.save(task);
        } else return null;
    }
}
