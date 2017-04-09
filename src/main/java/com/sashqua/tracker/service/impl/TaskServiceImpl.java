package com.sashqua.tracker.service.impl;

import com.sashqua.tracker.entitys.Task;
import com.sashqua.tracker.repository.TaskRepository;
import com.sashqua.tracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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
}
