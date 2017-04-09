package com.sashqua.tracker.service;

import com.sashqua.tracker.entitys.Role;
import com.sashqua.tracker.entitys.Task;

public interface TaskService {
    Task save(Task role);
    Task getTask(Integer id);
}
