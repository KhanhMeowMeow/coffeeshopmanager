package com.example.controller.DAO;

import java.util.List;

import com.example.model.Card;
import com.example.model.DTO.CardDTO;

public interface CardDAO{ 
    List<Card> findAll();
    Card findbyId(Long id);
    List<Card> findInBills();
    void create(CardDTO card);
    void update(Card data);
    void delete(Long id);
} 