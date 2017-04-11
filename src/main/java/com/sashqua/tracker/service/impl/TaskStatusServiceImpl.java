package com.sashqua.tracker.service.impl;

import com.sashqua.tracker.entitys.TaskStatus;
import com.sashqua.tracker.repository.TaskStatusRepository;
import com.sashqua.tracker.service.TaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service("taskStatusService")
@Validated
public class TaskStatusServiceImpl implements TaskStatusService {
    @Autowired
    private final TaskStatusRepository taskStatusRepository;

    public TaskStatusServiceImpl(TaskStatusRepository taskStatusRepository) {
        this.taskStatusRepository = taskStatusRepository;
    }


    @Override
    public TaskStatus save(TaskStatus status) {
        return null;
    }

    @Override
    public TaskStatus getTaskStatus(Integer id) {
        return null;
    }

    @Override
    public List<TaskStatus> getAll() {
        return taskStatusRepository.findAll();
    }
}
