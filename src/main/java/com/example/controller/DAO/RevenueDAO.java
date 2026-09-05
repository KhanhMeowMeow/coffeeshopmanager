package com.example.controller.DAO;

import java.util.Date;
import java.util.List;

import com.example.model.DTO.RevenueBillDTO;
import com.example.model.DTO.RevenueBill_UserDTO;
import com.example.model.DTO.RevenueUserDTO;

public interface RevenueDAO {
    List<RevenueBillDTO> findAllBill();
    List<RevenueBillDTO> findbyDateBill(Date date);
    List<RevenueBillDTO> findbyMonthBill(Date date);
    List<RevenueBillDTO> findbyYearBill(Date date);
    List<RevenueBillDTO> findbyDateAfterBill(Date date);
    List<RevenueBillDTO> findbyDateBeforeBill(Date date);
    List<RevenueBillDTO> findbyDateFromAndToBill(Date dateFrom, Date dateTo);
    List<RevenueUserDTO> findAllUser();
    List<RevenueUserDTO> findbyDateUser(Date date);
    List<RevenueUserDTO> findbyMonthUser(Date date);
    List<RevenueUserDTO> findbyYearUser(Date date);
    List<RevenueUserDTO> findbyDateAfterUser(Date date);
    List<RevenueUserDTO> findbyDateBeforeUser(Date date);
    List<RevenueUserDTO> findbyDateFromAndToUser(Date dateFrom, Date dateTo);
    List<RevenueBill_UserDTO> findAllBill_User(String userId);
    List<RevenueBill_UserDTO> findByDateBill_User(String userId, Date date);
    List<RevenueBill_UserDTO> findByWeekBill_User(String userId, Date date);
    List<RevenueBill_UserDTO> findByMonthBill_User(String userId, Date date);
    List<RevenueBill_UserDTO> findByYearBill_User(String userId, Date date);
    List<RevenueBill_UserDTO> findDate_FromBill_User(String userId, Date date);
    List<RevenueBill_UserDTO> findDate_ToBill_User(String userId, Date date);
    List<RevenueBill_UserDTO> findDate_ToANdBill_User(String userId, Date dateTo, Date dateFrom);
}
