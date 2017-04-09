package com.sashqua.tracker.service.impl;

import com.sashqua.tracker.entitys.User;
import com.sashqua.tracker.repository.UserRepository;
import com.sashqua.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;

/**
 * CinemaHouse project
 *
 * @Version 1.0
 * Created by Alexandr Shalukho.
 */
@Service("userService")
@Validated
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(Integer id) {
        return userRepository.getOne(id);
    }

    @Override
    public User findByLogin(String login) {
        User userr = new User();
        userr.setId(1);
        userr.setFirstName("admin");
        userr.setEmail("admin");
        userr.setPassword("admin");
        return userr;
    }
}
