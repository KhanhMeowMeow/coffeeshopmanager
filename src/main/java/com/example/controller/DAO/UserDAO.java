package com.example.controller.DAO;

import java.util.List;

import com.example.model.User;

public interface UserDAO{ 
    User findById(String userName);
    List<User> findAll(String username);
    List<User> findByName(String username);
    void create(User data);
    void update(User data);
    void delete(String id);
} 