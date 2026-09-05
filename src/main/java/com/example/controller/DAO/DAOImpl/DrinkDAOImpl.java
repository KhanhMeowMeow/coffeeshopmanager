package com.example.controller.DAO.DAOImpl;

import java.util.List;

import com.example.controller.DAO.DrinkDAO;
import com.example.controller.lib.JDBC;
import com.example.model.Drink;
import com.example.model.DTO.DrinkDTO;
import com.example.model.DTO.DrinkDTOCreate;
import com.example.model.DTO.DrinkOrderDTO;



public class DrinkDAOImpl implements DrinkDAO{

    @Override
    public List<DrinkDTO> findAll() {
        return JDBC.select("SELECT d.Id, d.name, d.UnitPrice, d.Discount, d.Image, d.Available, c.Name FROM Drinks d INNER JOIN Categories c on d.CategoryId = c.Id", DrinkDTO.class);
    }

    @Override
    public void create(DrinkDTOCreate data) {
        JDBC.insert("INSERT INTO Drinks (Name, UnitPrice, Discount, Image, Available, CategoryId, Disable) VALUES (?, ?, ?, ?, ?, ?, ?)", data);
    }

    @Override
    public void update(Drink data) {
        JDBC.update("UPDATE Drinks SET  Name = ?, UnitPrice = ?, Discount = ?, Image = ?, Available = ?, CategoryId = ?, Disable = ? WHERE Id = ?", data);
    }

    @Override
    public void delete(Long id) {
        JDBC.delete("DELETE Drinks WHERE Id = ?", id);
    }

    @Override
    public List<DrinkOrderDTO> findAllDrinkOrderDTO() {
        return JDBC.select("SELECT d.Id, d.Name, d.UnitPrice, d.Discount, d.Image, c.Name AS CategoryName, d.Available FROM Drinks d Inner JOIN Categories c ON d.CategoryId = c.Id WHERE d.Available = 1", DrinkOrderDTO.class);
    }

    @Override
    public Drink findById(Long id) {
        return JDBC.selectOneObject("Select * from Drinks WHERE Id = ?", Drink.class, id);
    }

    @Override
    public List<DrinkDTO> findByCardId(Long cardId) {
        return JDBC.selectbjectsOneValue("SELECT d.Id, d.Name, d.UnitPrice, d.Discount, d.Image, d.Available, c.Name, d.Disable FROM Drinks d INNER JOIN Categories c on d.CategoryId = c.Id Where c.Id = ?", DrinkDTO.class, cardId);
    }

    @Override
    public List<DrinkDTO> findByName(String name) {
        return JDBC.selectbjectsOneValue("SELECT d.Id, d.Name, d.UnitPrice, d.Discount, d.Image, d.Available, c.Name, d.Disable FROM Drinks d INNER JOIN Categories c on d.CategoryId = c.Id Where d.Name COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ?", DrinkDTO.class, "%" + name + "%");
    }

    @Override
    public List<DrinkDTO> findByACtive() {
        return JDBC.select("SELECT d.Id, d.name, d.UnitPrice, d.Discount, d.Image, d.Available, c.Name FROM Drinks d INNER JOIN Categories c on d.CategoryId = c.Id WHERE d.Available = 1", DrinkDTO.class);
    }
}
