package com.sashqua.tracker.service;

import com.sashqua.tracker.entitys.Task;
import com.sashqua.tracker.entitys.TaskStatus;

public interface TaskStatusService {
    TaskStatus save(TaskStatus status);
    TaskStatus getTaskStatus(Integer id);
}
