package com.sashqua.tracker.service.impl;

import com.sashqua.tracker.entitys.Role;
import com.sashqua.tracker.entitys.User;
import com.sashqua.tracker.repository.UserRepository;
import com.sashqua.tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import java.util.List;

@Service("userService")
@Validated
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findByLogin(String login) {
        User user = new User();
        user = userRepository.findByEmail(login);
        return user;
    }

    @Override
    public List<User> getAllDevelopers() {
        return userRepository.findByRole(Role.DEVELOPER);
    }
}
