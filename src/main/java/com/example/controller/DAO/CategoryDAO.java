package com.example.controller.DAO;

import java.util.List;
import com.example.model.Category;
import com.example.model.DTO.CategoryDTO;

public interface CategoryDAO{ 
    List<Category> findAll();
    List<Category> findByName(String name);
    Category findById(Long id);
    void create(CategoryDTO data);
    void update(Category data);
    void delete(Long id);
} 