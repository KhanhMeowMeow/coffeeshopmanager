package com.example.controller.DAO.DAOImpl;

import java.util.List;

import com.example.controller.DAO.CategoryDAO;
import com.example.controller.lib.JDBC;
import com.example.model.Category;
import com.example.model.DTO.CategoryDTO;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public List<Category> findAll() {
        return JDBC.select("SELECT * FROM Categories", Category.class);
    }

    @Override
    public void create(CategoryDTO data) {
        JDBC.insert("INSERT INTO Categories (Name, Disable) VALUES (?, ?)", data);
    }

    @Override
    public void update(Category data) {
        JDBC.update("UPDATE Categories SET Name = ?, Disable =  ? WHERE Id = ?", data);
    }

    @Override
    public void delete(Long id) {
        JDBC.delete("DELETE Categories WHERE Id = ?", id);
    }

    @Override
    public Category findById(Long id) {
        return JDBC.selectOneObject("SELECT * FROM Categories WHERE Id = ?", Category.class, id);
    }

    @Override
    public List<Category> findByName(String name) {
        return JDBC.selectbjectsOneValue("SELECT * FROM Categories WHERE Name COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ?", Category.class,"%" + name + "%");
    }
}
