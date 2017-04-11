package com.sashqua.tracker.service;

import com.sashqua.tracker.entitys.Task;
import com.sashqua.tracker.entitys.TaskStatus;

import java.util.List;

public interface TaskStatusService {
    TaskStatus save(TaskStatus status);
    TaskStatus getTaskStatus(Integer id);
    List<TaskStatus> getAll();
}
