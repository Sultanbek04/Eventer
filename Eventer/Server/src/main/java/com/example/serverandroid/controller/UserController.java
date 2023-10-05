package com.example.serverandroid.controller;

import com.example.serverandroid.entity.PasswordEncryption;
import com.example.serverandroid.entity.User;
import com.example.serverandroid.repository.UserRepository;
import com.example.serverandroid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    PasswordEncryption passwordEncryption;

    {
        try {
            passwordEncryption = new PasswordEncryption();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{email}/{password}")
    public User getUserByEmailAndPassword(@PathVariable("email") String email, @PathVariable("password") String password) {
        return userRepository.findUsersByEmailAndPassword(email, passwordEncryption.encrypt(password));
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        String newPassword = passwordEncryption.encrypt(user.getPassword());
        System.out.println("String To Encrypt: " + user.getPassword());
        user.setPassword(newPassword);
        System.out.println("Encrypted String:" + user.getPassword());
        System.out.println("Decrypted String:" + passwordEncryption.decrypt(user.getPassword()));

        return userService.saveOrUpdateUser(user);
    }


    @PutMapping("/user")
    public void updateUser(@RequestBody User user) {
        userService.saveOrUpdateUser(user);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
    }
}
