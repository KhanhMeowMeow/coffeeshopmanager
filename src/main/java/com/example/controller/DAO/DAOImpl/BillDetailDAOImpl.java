package com.example.controller.DAO.DAOImpl;

import java.util.List;

import com.example.controller.DAO.BillDetailDAO;
import com.example.controller.lib.JDBC;
import com.example.model.BillDetail;
import com.example.model.DTO.BillDetailDTO;
import com.example.model.DTO.Bill_DrinkOrder;

public class BillDetailDAOImpl implements BillDetailDAO {

    @Override
    public List<BillDetailDTO> findAll() {
        return JDBC.select("Select BillId, DrinkId, UnitPrice, Discount, Quantity, Disable from Cards", BillDetailDTO.class);
    }

    @Override
    public void create(BillDetailDTO data) {
        JDBC.insert("INSERT INTO BillDetails (BillId, DrinkId, UnitPrice, Discount, Quantity, Disable) VALUES (?, ?, ?, ?, ?, ?)", data);
    }

    @Override
    public void update(BillDetail data) {
        JDBC.update("UPDATE BillDetails SET BillId = ?, DrinkId = ?, UnitPrice = ?, Discount = ?, Quantity = ?, Disable = ? WHERE Id = ?", data);
    }

    @Override
    public void delete(Long id) {
        JDBC.delete("DELETE BillDetails WHERE Id = ?", id);
    }

    @Override
    public List<Bill_DrinkOrder> findBillDrinkOrdersByBillId(Long billId) {
        return JDBC.selectbjectsOneValue("SELECT bd.Id, d.Name as drinkname, d.Image, bd.UnitPrice as price, bd.Quantity as quantity, (bd.UnitPrice * bd.Quantity) as total FROM BillDetails bd JOIN Drinks d ON bd.DrinkId = d.Id WHERE bd.BillId = ?", Bill_DrinkOrder.class, billId);
    }

    @Override
    public BillDetail findById(Long Id) {
        return JDBC.selectOneObject("Select * from BillDetails WHERE Id = ?", BillDetail.class, Id);
    }
    
    

}