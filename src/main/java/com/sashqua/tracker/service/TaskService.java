package com.sashqua.tracker.service;

import com.sashqua.tracker.entitys.Role;
import com.sashqua.tracker.entitys.Task;
import com.sashqua.tracker.entitys.User;

import java.util.List;
import java.util.Set;

public interface TaskService {
    Task save(Task role);
    Task getTask(Integer id);
    Task addUsers(Integer id, Set<User> users);
}
