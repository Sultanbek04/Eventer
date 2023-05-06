package com.example.serverandroid.service;

import com.example.serverandroid.entity.User;
import com.example.serverandroid.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User saveOrUpdateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User findUsersByEmailAndPassword(String email, String password) {
        return userRepository.findUsersByEmailAndPassword(email, password);
    }

    ;


    @Override
    @Transactional

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }


    @Override
    @Transactional
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

}
