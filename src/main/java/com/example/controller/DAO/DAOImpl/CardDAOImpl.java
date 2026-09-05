package com.example.controller.DAO.DAOImpl;

import java.util.List;

import com.example.controller.DAO.CardDAO;
import com.example.controller.lib.JDBC;
import com.example.model.Card;
import com.example.model.DTO.CardDTO;



public class CardDAOImpl implements CardDAO{

    @Override
    public List<Card> findAll() {
       return JDBC.select("Select * from Cards", Card.class);
    }

    @Override
    public void create(CardDTO data) {
        JDBC.insert("INSERT INTO Cards(Status, Disable) VALUES (?, ?)", data);
    }

    @Override
    public void update(Card data) {
        JDBC.update("UPDATE Cards SET Status = ?, Disable = ? WHERE Id = ?", data);
    }

    @Override
    public void delete(Long id) {
        JDBC.delete("Delete Cards where Id = ?", id);
    }

    @Override
    public List<Card> findInBills() {
        return JDBC.select("SELECT c1.Id, c1.Status, c1.Disable FROM Cards c1 INNER JOIN (SELECT CardId from Bills WHERE Status = 1) c2 ON c1.Id = c2.CardId where c1.Status = 0", Card.class);
    }

    @Override
    public Card findbyId(Long id) {
        return JDBC.selectOneObject("Select * from Cards WHERE Id = ?", Card.class, id);
    }
}
