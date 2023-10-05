package com.example.serverandroid.service;

import com.example.serverandroid.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User saveOrUpdateUser(User user);

    User findUsersByEmailAndPassword(String email, String password);

    User getUserById(int id);

    void deleteUserById(int id);
}
