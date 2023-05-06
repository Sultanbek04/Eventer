package com.example.serverandroid.repository;

import com.example.serverandroid.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUsersByEmailAndPassword(String email, String password);

}
