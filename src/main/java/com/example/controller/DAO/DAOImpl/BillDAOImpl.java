
package com.example.controller.DAO.DAOImpl;

import java.util.Date;
import java.util.List;

import com.example.controller.DAO.BillDAO;
import com.example.controller.lib.DateTimeSYSTEM;
import com.example.controller.lib.JDBC;
import com.example.model.Bill;
import com.example.model.DTO.BillDTO;
import com.example.model.DTO.BillDTOCreate;

public class BillDAOImpl implements BillDAO {

    @Override
    public List<Bill> findAll(){        
        return JDBC.select("Select * from Bills", Bill.class);
    }

    @Override
    public void create(BillDTOCreate data) {
        JDBC.insert("INSERT INTO Bills(Username, CardId, Checkin, Checkout, Status, Disable) VALUES (?, ?, ?, ?, ?, ?)", data);
    }

    @Override
    public void update(Bill data) {
        JDBC.update("UPDATE Bills SET Username = ? , CardId = ?, Checkin = ?, Checkout = ?, Status = ?, Disable = ? WHERE Id = ?", data);
    }

    @Override
    public void delete(Long id) {
        JDBC.delete("Delete Bills where Id = ?", id);
    }
    
    @Override
    public List<BillDTO> findAllByDate(Date date) {
        return JDBC.selectbjectsOneValue("SELECT b.Id, u.Fullname, b.CardId, b.Checkin, b.Checkout, b.Status FROM Bills b INNER JOIN Users u on b.Username = u.Username WHERE Cast(b.Checkin as Date) = ? OR b.Status = 1 OR b.Status = 0", BillDTO.class, DateTimeSYSTEM.toSqlDate(date));
    }

    @Override
    public Bill findById(Long Id) {
        return JDBC.selectOneObject("Select * from Bills where Id = ?", Bill.class, Id);
    }
}

