package com.example.controller.DAO.DAOImpl;

import java.util.Date;
import java.util.List;

import com.example.controller.DAO.RevenueDAO;
import com.example.controller.lib.DateTimeSYSTEM;
import com.example.controller.lib.JDBC;
import com.example.model.DTO.RevenueBillDTO;
import com.example.model.DTO.RevenueBill_UserDTO;
import com.example.model.DTO.RevenueUserDTO;

public class RevenueDAOImpl implements RevenueDAO {

    @Override
    public List<RevenueBillDTO> findbyDateBill(Date date) {
        return JDBC.selectbjectsOneValue("Select ct.Name, s1.TotalPrice, s1.TotalQuantity, s1.MinPrice, s1.MaxPrice, s1.AvgPrice from Categories ct\r\n" + //
                        "INNER JOIN (SELECT d.CategoryId as ctId, SUM(bd.UnitPrice * bd.Quantity) as TotalPrice, Sum(bd.Quantity) as TotalQuantity, Min(d.UnitPrice) as MinPrice, Max(d.UnitPrice) as MaxPrice, Avg(d.UnitPrice) as AvgPrice from \r\n" + //
                        "(SELECT BillDetails.Id as Id, BillDetails.DrinkId as DrinkId, BillDetails.UnitPrice as UnitPrice, BillDetails.Quantity as Quantity FROM Bills \r\n" + //
                        "INNER JOIN BillDetails ON Bills.Id = BillDetails.BillId WHERE Cast(Bills.Checkout as Date) = ?) bd \r\n" + //
                        "INNER JOIN Drinks d on d.Id = bd.DrinkId\r\n" + //
                        "GROUP BY d.CategoryId) s1 on ct.Id = s1.ctId", RevenueBillDTO.class, DateTimeSYSTEM.toSqlDate(date));
    }

    @Override
    public List<RevenueBillDTO> findbyMonthBill(Date date) {
        return JDBC.selectbjectsTwoValue("Select ct.Name, s1.TotalPrice, s1.TotalQuantity, s1.MinPrice, s1.MaxPrice, s1.AvgPrice from Categories ct\r\n" + //
                        "INNER JOIN (SELECT d.CategoryId as ctId, SUM(bd.UnitPrice * bd.Quantity) as TotalPrice, Sum(bd.Quantity) as TotalQuantity, Min(d.UnitPrice) as MinPrice, Max(d.UnitPrice) as MaxPrice, Avg(d.UnitPrice) as AvgPrice from (SELECT BillDetails.Id as Id, BillDetails.DrinkId as DrinkId, BillDetails.UnitPrice as UnitPrice, BillDetails.Quantity as Quantity FROM Bills \r\n" + //
                        "INNER JOIN BillDetails ON Bills.Id = BillDetails.BillId WHERE Month(Bills.Checkout) = Month(?) AND Year(Bills.Checkout) = Year(?)) bd \r\n" + //
                        "INNER JOIN Drinks d on d.Id = bd.DrinkId\r\n" + //
                        "GROUP BY d.CategoryId) s1 on ct.Id = s1.ctId", RevenueBillDTO.class,  DateTimeSYSTEM.toSqlDate(date), DateTimeSYSTEM.toSqlDate(date));
    }

    @Override
    public List<RevenueBillDTO> findbyYearBill(Date date) {
        return JDBC.selectbjectsOneValue("Select ct.Name, s1.TotalPrice, s1.TotalQuantity, s1.MinPrice, s1.MaxPrice, s1.AvgPrice from Categories ct\r\n" + //
                        "INNER JOIN (SELECT d.CategoryId as ctId, SUM(bd.UnitPrice * bd.Quantity) as TotalPrice, Sum(bd.Quantity) as TotalQuantity, Min(d.UnitPrice) as MinPrice, Max(d.UnitPrice) as MaxPrice, Avg(d.UnitPrice) as AvgPrice from (SELECT BillDetails.Id as Id, BillDetails.DrinkId as DrinkId, BillDetails.UnitPrice as UnitPrice, BillDetails.Quantity as Quantity FROM Bills \r\n" + //
                        "INNER JOIN BillDetails ON Bills.Id = BillDetails.BillId WHERE Year(Bills.Checkout) = Year(?)) bd \r\n" + //
                        "INNER JOIN Drinks d on d.Id = bd.DrinkId\r\n" + // 
                        "GROUP BY d.CategoryId) s1 on ct.Id = s1.ctId", RevenueBillDTO.class, DateTimeSYSTEM.toSqlDate(date));
    }

    @Override
    public List<RevenueBillDTO> findbyDateAfterBill(Date date) {
        return JDBC.selectbjectsOneValue("Select ct.Name, s1.TotalPrice, s1.TotalQuantity, s1.MinPrice, s1.MaxPrice, s1.AvgPrice from Categories ct\r\n" + //
                        "INNER JOIN (SELECT d.CategoryId as ctId, SUM(bd.UnitPrice * bd.Quantity) as TotalPrice, Sum(bd.Quantity) as TotalQuantity, Min(d.UnitPrice) as MinPrice, Max(d.UnitPrice) as MaxPrice, Avg(d.UnitPrice) as AvgPrice from (SELECT BillDetails.Id as Id, BillDetails.DrinkId as DrinkId, BillDetails.UnitPrice as UnitPrice, BillDetails.Quantity as Quantity FROM Bills \r\n" + //
                        "INNER JOIN BillDetails ON Bills.Id = BillDetails.BillId WHERE Cast(Bills.Checkout as Date) <= ?) bd \r\n" + //
                        "INNER JOIN Drinks d on d.Id = bd.DrinkId\r\n" + //
                        "GROUP BY d.CategoryId) s1 on ct.Id = s1.ctId", RevenueBillDTO.class, DateTimeSYSTEM.toSqlDate(date));
    }

    @Override
    public List<RevenueBillDTO> findbyDateBeforeBill(Date date) {
        return JDBC.selectbjectsOneValue("Select ct.Name, s1.TotalPrice, s1.TotalQuantity, s1.MinPrice, s1.MaxPrice, s1.AvgPrice from Categories ct\r\n" + //
                        "INNER JOIN (SELECT d.CategoryId as ctId, SUM(bd.UnitPrice * bd.Quantity) as TotalPrice, Sum(bd.Quantity) as TotalQuantity, Min(d.UnitPrice) as MinPrice, Max(d.UnitPrice) as MaxPrice, Avg(d.UnitPrice) as AvgPrice from (SELECT BillDetails.Id as Id, BillDetails.DrinkId as DrinkId, BillDetails.UnitPrice as UnitPrice, BillDetails.Quantity as Quantity FROM Bills \r\n" + //
                        "INNER JOIN BillDetails ON Bills.Id = BillDetails.BillId WHERE Cast(Bills.Checkout as Date >= ?) bd \r\n" + //
                        "INNER JOIN Drinks d on d.Id = bd.DrinkId\r\n" + //
                        "GROUP BY d.CategoryId) s1 on ct.Id = s1.ctId", RevenueBillDTO.class, DateTimeSYSTEM.toSqlDate(date));
    }

    @Override
    public List<RevenueBillDTO> findbyDateFromAndToBill(Date dateFrom, Date dateTo) {
        return JDBC.selectbjectsTwoValue("Select ct.Name, s1.TotalPrice, s1.TotalQuantity, s1.MinPrice, s1.MaxPrice, s1.AvgPrice from Categories ct\r\n" + //
                        "INNER JOIN (SELECT d.CategoryId as ctId, SUM(bd.UnitPrice * bd.Quantity) as TotalPrice, Sum(bd.Quantity) as TotalQuantity, Min(d.UnitPrice) as MinPrice, Max(d.UnitPrice) as MaxPrice, Avg(d.UnitPrice) as AvgPrice from (SELECT BillDetails.Id as Id, BillDetails.DrinkId as DrinkId, BillDetails.UnitPrice as UnitPrice, BillDetails.Quantity as Quantity FROM Bills \r\n" + //
                        "INNER JOIN BillDetails ON Bills.Id = BillDetails.BillId WHERE Cast(Bills.Checkout as Date) >= ? and Cast(Bills.Checkout as Date) <= ?) bd \r\n" + //
                        "INNER JOIN Drinks d on d.Id = bd.DrinkId\r\n" + //
                        "GROUP BY d.CategoryId) s1 on ct.Id = s1.ctId", RevenueBillDTO.class, DateTimeSYSTEM.toSqlDate(dateFrom), DateTimeSYSTEM.toSqlDate(dateTo));
    }

    @Override
    public List<RevenueBillDTO> findAllBill() {
        return JDBC.select("Select ct.Name, s1.TotalPrice, s1.TotalQuantity, s1.MinPrice, s1.MaxPrice, s1.AvgPrice from Categories ct\r\n" + //
                        "INNER JOIN (SELECT d.CategoryId as ctId, SUM(bd.UnitPrice * bd.Quantity) as TotalPrice, Sum(bd.Quantity) as TotalQuantity, Min(d.UnitPrice) as MinPrice, Max(d.UnitPrice) as MaxPrice, Avg(d.UnitPrice) as AvgPrice from (SELECT BillDetails.Id as Id, BillDetails.DrinkId as DrinkId, BillDetails.UnitPrice as UnitPrice, BillDetails.Quantity as Quantity FROM Bills \r\n" + //
                        "INNER JOIN BillDetails ON Bills.Id = BillDetails.BillId ) bd \r\n" + //
                        "INNER JOIN Drinks d on d.Id = bd.DrinkId\r\n" + //
                        "GROUP BY d.CategoryId) s1 on ct.Id = s1.ctId", RevenueBillDTO.class);
    }

    @Override
    public List<RevenueUserDTO> findAllUser() {
        return JDBC.select("SELECT b2.Username as Username, Sum(s1.TotalPrice) as TotalPrice, Sum(s1.TotalQuantityBill) as TotalQuantityBill, Min(b2.Checkout) as FirsDate, Max(b2.Checkout) as FinalDate FROM Bills b2\r\n" + //
                        "INNER JOIN (SELECT b.Id as bId, SUM(bd.UnitPrice) as TotalPrice, COUNT(bd.BillId) as TotalQuantityBill FROM BillDetails bd\r\n" + //
                        "INNER JOIN Bills b ON b.id = bd.BillId GROUP BY b.Id) s1\r\n" + //
                        "on s1.bId = b2.Id GROUP BY Username", RevenueUserDTO.class);
    }

    @Override
    public List<RevenueUserDTO> findbyDateUser(Date date) {
        return JDBC.selectbjectsOneValue("SELECT b2.Username as Username, Sum(s1.TotalPrice) as TotalPrice, Sum(s1.TotalQuantityBill) as TotalQuantityBill, Min(b2.Checkout) as FirsDate, Max(b2.Checkout) as FinalDate FROM Bills b2\r\n" + //
                        "INNER JOIN (SELECT b.Id as bId, SUM(bd.UnitPrice) as TotalPrice, COUNT(bd.BillId) as TotalQuantityBill FROM BillDetails bd\r\n" + //
                        "INNER JOIN Bills b ON b.id = bd.BillId WHERE Cast(b.Checkout as Date) = ? GROUP BY b.Id) s1\r\n" + //
                        "on s1.bId = b2.Id GROUP BY Username", RevenueUserDTO.class, DateTimeSYSTEM.toSqlDate(date));
    }

    @Override
    public List<RevenueUserDTO> findbyMonthUser(Date date) {
        return JDBC.selectbjectsTwoValue("SELECT b2.Username as Username, Sum(s1.TotalPrice) as TotalPrice, Sum(s1.TotalQuantityBill) as TotalQuantityBill, Min(b2.Checkout) as FirsDate, Max(b2.Checkout) as FinalDate FROM Bills b2\r\n" + //
                        "INNER JOIN (SELECT b.Id as bId, SUM(bd.UnitPrice) as TotalPrice, COUNT(bd.BillId) as TotalQuantityBill FROM BillDetails bd\r\n" + //
                        "INNER JOIN Bills b ON b.id = bd.BillId WHERE Month(b.Checkout) = Month(?) AND Year(b.Checkout) = Year(?) GROUP BY b.Id) s1\r\n" + //
                        "on s1.bId = b2.Id GROUP BY Username", RevenueUserDTO.class, DateTimeSYSTEM.toSqlDate(date),  DateTimeSYSTEM.toSqlDate(date));
    }

    @Override
    public List<RevenueUserDTO> findbyYearUser(Date date) {
        return JDBC.selectbjectsOneValue("SELECT b2.Username as Username, Sum(s1.TotalPrice) as TotalPrice, Sum(s1.TotalQuantityBill) as TotalQuantityBill, Min(b2.Checkout) as FirsDate, Max(b2.Checkout) as FinalDate FROM Bills b2\r\n" + //
                        "INNER JOIN (SELECT b.Id as bId, SUM(bd.UnitPrice) as TotalPrice, COUNT(bd.BillId) as TotalQuantityBill FROM BillDetails bd\r\n" + //
                        "INNER JOIN Bills b ON b.id = bd.BillId WHERE Year(b.Checkout) = Year(?) GROUP BY b.Id) s1\r\n" + //
                        "on s1.bId = b2.Id GROUP BY Username", RevenueUserDTO.class, DateTimeSYSTEM.toSqlDate(date));
    }

    @Override
    public List<RevenueUserDTO> findbyDateAfterUser(Date date) {
        return JDBC.selectbjectsOneValue("SELECT b2.Username as Username, Sum(s1.TotalPrice) as TotalPrice, Sum(s1.TotalQuantityBill) as TotalQuantityBill, Min(b2.Checkout) as FirsDate, Max(b2.Checkout) as FinalDate FROM Bills b2\r\n" + //
                        "INNER JOIN (SELECT b.Id as bId, SUM(bd.UnitPrice) as TotalPrice, COUNT(bd.BillId) as TotalQuantityBill FROM BillDetails bd\r\n" + //
                        "INNER JOIN Bills b ON b.id = bd.BillId WHERE Cast(b.Checkout as Date) <= ? GROUP BY b.Id) s1\r\n" + //
                        "on s1.bId = b2.Id GROUP BY Username", RevenueUserDTO.class, DateTimeSYSTEM.toSqlDate(date));
    }

    @Override
    public List<RevenueUserDTO> findbyDateBeforeUser(Date date) {
        return JDBC.selectbjectsOneValue("SELECT b2.Username as Username, Sum(s1.TotalPrice) as TotalPrice, Sum(s1.TotalQuantityBill) as TotalQuantityBill, Min(b2.Checkout) as FirsDate, Max(b2.Checkout) as FinalDate FROM Bills b2\r\n" + //
                        "INNER JOIN (SELECT b.Id as bId, SUM(bd.UnitPrice) as TotalPrice, COUNT(bd.BillId) as TotalQuantityBill FROM BillDetails bd\r\n" + //
                        "INNER JOIN Bills b ON b.id = bd.BillId WHERE Cast(b.Checkout as Date) >= ? GROUP BY b.Id) s1\r\n" + //
                        "on s1.bId = b2.Id GROUP BY Username", RevenueUserDTO.class, DateTimeSYSTEM.toSqlDate(date));
    }

    @Override
    public List<RevenueUserDTO> findbyDateFromAndToUser(Date dateFrom, Date dateTo) {
        return JDBC.selectbjectsTwoValue("SELECT b2.Username as Username, Sum(s1.TotalPrice) as TotalPrice, Sum(s1.TotalQuantityBill) as TotalQuantityBill, Min(b2.Checkout) as FirsDate, Max(b2.Checkout) as FinalDate FROM Bills b2\r\n" + //
                        "INNER JOIN (SELECT b.Id as bId, SUM(bd.UnitPrice) as TotalPrice, COUNT(bd.BillId) as TotalQuantityBill FROM BillDetails bd\r\n" + //
                        "INNER JOIN Bills b ON b.id = bd.BillId WHERE Cast(b.Checkout as Date) >= ? And Cast(b.Checkout as Date) <= ? GROUP BY b.Id) s1\r\n" + //
                        "on s1.bId = b2.Id GROUP BY Username", RevenueUserDTO.class, DateTimeSYSTEM.toSqlDate(dateFrom), DateTimeSYSTEM.toSqlDate(dateTo));
    }

    @Override
    public List<RevenueBill_UserDTO> findAllBill_User(String userId) {
        return JDBC.selectbjectsOneValue("SELECT Id, CardId, Checkin, Checkout, Status from Bills WHERE Username = ?", RevenueBill_UserDTO.class, userId);
    }

    @Override
    public List<RevenueBill_UserDTO> findByDateBill_User(String userId, Date date) {
        return JDBC.selectbjectsTwoValue("SELECT Id, CardId, Checkin, Checkout, Status from Bills WHERE Username = ? AND Cast(Checkin as Date) = ?", RevenueBill_UserDTO.class, userId, DateTimeSYSTEM.toSqlDate(date));
    }

    @Override
    public List<RevenueBill_UserDTO> findByWeekBill_User(String userId, Date date) {
        return JDBC.selectbjectsTwoValue("SELECT Id, CardId, Checkin, Checkout, Status from Bills WHERE Username = ? AND DATEPART(WEEK, Checkin) = DATEPART(WEEK, ?)", RevenueBill_UserDTO.class, userId, DateTimeSYSTEM.toSqlDate(date));
    }

    @Override
    public List<RevenueBill_UserDTO> findByMonthBill_User(String userId, Date date) {
        return JDBC.selectbjectsTwoValue("SELECT Id, CardId, Checkin, Checkout, Status from Bills WHERE Username = ? AND Month(Checkin) =  Month(?)", RevenueBill_UserDTO.class, userId, DateTimeSYSTEM.toSqlDate(date));
    }

    @Override
    public List<RevenueBill_UserDTO> findByYearBill_User(String userId, Date date) {
        return JDBC.selectbjectsTwoValue("SELECT Id, CardId, Checkin, Checkout, Status from Bills WHERE Username = ? AND Year(Checkin) = Year(?)", RevenueBill_UserDTO.class, userId, DateTimeSYSTEM.toSqlDate(date));
    }

    @Override
    public List<RevenueBill_UserDTO> findDate_FromBill_User(String userId, Date date) {
        return JDBC.selectbjectsTwoValue("SELECT Id, CardId, Checkin, Checkout, Status from Bills WHERE Username = ? AND Cast(Checkin as Date) >= ?", RevenueBill_UserDTO.class, userId, DateTimeSYSTEM.toSqlDate(date));
    }

    @Override
    public List<RevenueBill_UserDTO> findDate_ToBill_User(String userId, Date date) {
        return JDBC.selectbjectsTwoValue("SELECT Id, CardId, Checkin, Checkout, Status from Bills WHERE Username = ? AND Cast(Checkin as Date) <= ?", RevenueBill_UserDTO.class, userId, DateTimeSYSTEM.toSqlDate(date));
    }

    @Override
    public List<RevenueBill_UserDTO> findDate_ToANdBill_User(String userId, Date dateFrom, Date dateTo) {
        return JDBC.selectbjectsThreeValue("SELECT Id, CardId, Checkin, Checkout, Status from Bills WHERE Username = ? AND Cast(Checkin as Date) >= ? AND Cast(Checkin as Date) <= ?", RevenueBill_UserDTO.class, userId, DateTimeSYSTEM.toSqlDate(dateFrom), DateTimeSYSTEM.toSqlDate(dateTo));
    }

    
}
