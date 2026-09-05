package com.example.controller.DAO;

import java.util.Date;
import java.util.List;

import com.example.model.Bill;
import com.example.model.DTO.BillDTO;
import com.example.model.DTO.BillDTOCreate;

public interface BillDAO{ 
    List<Bill> findAll();
    List<BillDTO> findAllByDate(Date date);
    Bill findById(Long Id);
    void create(BillDTOCreate data);
    void update(Bill data);
    void delete(Long id);
}