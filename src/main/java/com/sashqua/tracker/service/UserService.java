package com.sashqua.tracker.service;

import com.sashqua.tracker.entitys.User;

public interface UserService {
    User save(User user);
    User getUser(Integer id);
    User findByLogin(String login);
}
