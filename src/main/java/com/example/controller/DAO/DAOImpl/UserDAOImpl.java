package com.example.controller.DAO.DAOImpl;

import java.util.List;

import com.example.controller.DAO.UserDAO;
import com.example.controller.lib.JDBC;
import com.example.model.User;

public class UserDAOImpl implements UserDAO{

    @Override
    public List<User> findAll(String username) {
        return JDBC.selectbjectsOneValue("Select * from Users WHERE Username != ?", User.class, username);
    }

    @Override
    public void create(User data) {
        JDBC.insert("INSERT INTO Users (Username, Password, Enabled, Fullname, Photo, Manager, Disable) VALUES (?, ?, ?, ?, ?, ?, ?)", data);
        
    }

    @Override
    public void update(User data) {
        JDBC.update("UPDATE Users SET Password = ?, Enabled = ?, Fullname = ?, Photo = ?, Manager = ?, Disable = ? WHERE Username = ?", data);
    }

    @Override
    public void delete(String id) {
        JDBC.delete("DELETE Users WHERE Username = ?", id);
    }

    @Override
    public User findById(String username) {
        return JDBC.selectOneObject("Select * from Users WHERE Username = ?", User.class, username);
    }

    @Override
    public List<User> findByName(String username) {
        return JDBC.selectbjectsOneValue("Select * from Users WHERE Fullname COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ?", User.class,"%"  + username + "%");
    }
}
