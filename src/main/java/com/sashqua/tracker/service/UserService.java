package com.sashqua.tracker.service;

import com.sashqua.tracker.entitys.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User save(User user);
    User getUser(Integer id);
    User findByLogin(String login);
    List<User> getAllDevelopers();
}
