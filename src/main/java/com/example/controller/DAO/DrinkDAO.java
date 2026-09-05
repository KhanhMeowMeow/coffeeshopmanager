package com.example.controller.DAO;

import java.util.List;

import com.example.model.Drink;
import com.example.model.DTO.DrinkDTO;
import com.example.model.DTO.DrinkDTOCreate;
import com.example.model.DTO.DrinkOrderDTO;

public interface DrinkDAO{ 
    List<DrinkDTO> findAll();
    List<DrinkDTO> findByACtive();
    List<DrinkDTO> findByCardId(Long cardId);
    List<DrinkDTO> findByName(String name);
    List<DrinkOrderDTO> findAllDrinkOrderDTO();
    void create(DrinkDTOCreate data);
    Drink findById(Long id);
    void update(Drink data);
    void delete(Long id);
} 