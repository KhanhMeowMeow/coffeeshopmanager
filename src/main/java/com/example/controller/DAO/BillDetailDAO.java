package com.example.controller.DAO;

import java.util.List;

import com.example.model.BillDetail;
import com.example.model.DTO.BillDetailDTO;
import com.example.model.DTO.Bill_DrinkOrder;

public interface BillDetailDAO { 
    List<BillDetailDTO> findAll();
    BillDetail findById(Long Id);
    List<Bill_DrinkOrder> findBillDrinkOrdersByBillId(Long billId);
    void create(BillDetailDTO data);
    void update(BillDetail data);
    void delete(Long id);
}