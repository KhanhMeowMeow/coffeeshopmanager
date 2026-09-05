use coffeeshopmanager

SELECT d.Id, d.name, d.UnitPrice, d.Discount, d.Image, d.Available, c.Name FROM Drinks d INNER JOIN Categories c on d.CategoryId = c.Id 

SELECT * FROM Drinks

SELECT * FROM Categories

UPDATE "Cards" SET "Disable" = 0

SELECT * FROM Cards

SELECT c1.Id, c1.Status, c1.Disable FROM Cards c1 INNER JOIN (SELECT CardId from Bills WHERE Status = 1) c2 ON c1.Id = c2.CardId where c1.Status = 0

INSERT INTO Users (Username, Password, Enabled, Fullname, Photo, Manager, Disable) 
VALUE ('', '', 0, N'', N'', 0, 0);

UPDATE Users 
SET Disable = 0
where Username = 'user020'

UPDATE Users SET Username = ?,Password = ?, Enabled = ?, Fullname = ?, Photo = ?, Manager = ?, Disable = ? WHERE Username = 'user020'

DELETE Users WHERE Id = ?
SELECT * FROM Categories


DELETE Categories

INSERT INTO Categories (Id, Name) 
VALUES (' ', N' ');

UPDATE Categories
SET Disable = 0
where Id = 0

UPDATE Categories SET
    Id = 0,
    Name = '',
where Id = ''

SELECT * FROM "Bills"

INSERT INTO Bills(Username, CardId, Checkin, Checkout, Status, Disable) VALUES (
    'user020', 2, GETDATE(), GETDATE(), 10, 1 
)

INSERT INTO Drinks(Id, Name, UnitPrice, Discount, Image, Available, CategoryId, Disable) VALUES ()


SELECT * from "Drinks"

SELECT BillId, DrinkId, UnitPrice, Discount, Quantity, Disable FROM BillDetails

INSERT INTO BillDetails(BillId, DrinkId, UnitPrice, Discount, Quantity, Disable) VALUES ()

UPDATE BillDetails SET BillId = ?, DrinkId = ?, UnitPrice = ?, Discount = ?, Quantity = ?, Disable WHERE Id = ?

DELETE BillDetails WHERE Id = ?

SELECT Name, Disable FROM Categories

UPDATE BillDetails SET Name = ?, Disable =  ? WHERE Id = ?

DELETE BillDetails WHERE Id = ?

SELECT Name, UnitPrice, Discount, Image, Available, CategoryId, Disable FROM Drinks

UPDATE Drinks SET  Name, UnitPrice, Discount, Image, Available, CategoryId, Disable WHERE Id = ?

DELETE Drinks WHERE Id = ?

SELECT Name, Disable FROM Categories

SELECT * from Bills where Year(Checkin) = 2025 and Month(Checkin) = 9

SELECT * from Bills where CAST(Checkout AS DATE) <= '2025-10-11'

Select * from BillDetails

SELECT * from Drinks

select * from Categories

select * from cards

SELECT d.Name, d.UnitPrice, d.Discount, d.Image, c.Name AS CategoryName, d.Available FROM Drinks d Inner JOIN Categories c ON d.CategoryId = c.Id

SELECT d.Name as drinkname, d.Image, bd.UnitPrice as price, bd.Quantity as quantity, (bd.UnitPrice * bd.Quantity) as total FROM BillDetails bd JOIN Drinks d ON bd.DrinkId = d.Id WHERE bd.BillId = 23

SELECT CardId from Bills WHERE Status = 1

SELECT c1.Id, c1.Status, c1.Disable FROM Cards c1 INNER JOIN (SELECT CardId from Bills WHERE Status = 1) c2 ON c1.Id = c2.CardId

SELECT b.Id, u.Fullname, b.CardId, b.Checkin, b.Checkout, b.Status FROM Bills b INNER JOIN Users u on b.Username = u.Username  

SELECT BillDetails.Id as Id, BillDetails.DrinkId as DrinkId, BillDetails.UnitPrice as UnitPrice, BillDetails.Quantity as Quantity FROM Bills 
INNER JOIN BillDetails ON Bills.Id = BillDetails.BillId WHERE Cast(Bills.Checkout as Date) = '2025-10-11'

SELECT d.CategoryId as ctId, SUM(bd.UnitPrice * bd.Quantity) as TotalPrice, Sum(bd.Quantity) as TotalQuantity, Min(d.UnitPrice) as MinPrice, Max(d.UnitPrice) as MaxPrice, Avg(d.UnitPrice) as AvgPrice from 
(SELECT BillDetails.Id as Id, BillDetails.DrinkId as DrinkId, BillDetails.UnitPrice as UnitPrice, BillDetails.Quantity as Quantity FROM Bills 
INNER JOIN BillDetails ON Bills.Id = BillDetails.BillId WHERE Cast(Bills.Checkout as Date) = '2025-10-11') bd 
INNER JOIN Drinks d on d.Id = bd.DrinkId
GROUP BY d.CategoryId

Select ct.Name, s1.TotalPrice, s1.TotalQuantity, s1.MinPrice, s1.MaxPrice, s1.AvgPrice from Categories ct
INNER JOIN (SELECT d.CategoryId as ctId, SUM(bd.UnitPrice * bd.Quantity) as TotalPrice, Sum(bd.Quantity) as TotalQuantity, Min(d.UnitPrice) as MinPrice, Max(d.UnitPrice) as MaxPrice, Avg(d.UnitPrice) as AvgPrice from 
(SELECT BillDetails.Id as Id, BillDetails.DrinkId as DrinkId, BillDetails.UnitPrice as UnitPrice, BillDetails.Quantity as Quantity FROM Bills 
INNER JOIN BillDetails ON Bills.Id = BillDetails.BillId) bd 
INNER JOIN Drinks d on d.Id = bd.DrinkId
GROUP BY d.CategoryId) s1 on ct.Id = s1.ctId


SELECT * FROM Cards

UPDATE Cards SET Status = 0

SELECT * FROM Bills

UPDATE Bills Set status = 0

SELECT * FROM BillDetails


SELECT b.Id as bId, SUM(bd.UnitPrice) as TotalPrice, COUNT(bd.BillId) as TotalQuantityBill FROM BillDetails bd
INNER JOIN Bills b ON b.id = bd.BillId WHERE Cast(b.Checkout as Date) = '2025-10-11' GROUP BY b.Id

SELECT b2.Username as Username, Sum(s1.TotalPrice) as TotalPrice, Sum(s1.TotalQuantityBill) as TotalQuantityBill, Min(b2.Checkout) as FirsDate, Max(b2.Checkout) as FinalDate FROM Bills b2
INNER JOIN (SELECT b.Id as bId, SUM(bd.UnitPrice) as TotalPrice, COUNT(bd.BillId) as TotalQuantityBill FROM BillDetails bd
INNER JOIN Bills b ON b.id = bd.BillId GROUP BY b.Id) s1
on s1.bId = b2.Id GROUP BY Username

SELECT Id, CardId, Checkin, Checkout, Status from Bills WHERE Username = 'user001'