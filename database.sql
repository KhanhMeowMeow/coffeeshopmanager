-- Active: 1760259294479@@SIEUMAYTINHKHANHSQLSERVER@1433@master
CREATE DATABASE coffeeshopmanager

use coffeeshopmanager

CREATE TABLE Categories(
    Id BIGINT NOT NULL IDENTITY,
    Name NVARCHAR(50),
    Disable BIT NOT NULL,
    PRIMARY KEY(Id)
)

CREATE TABLE Drinks(
    Id BIGINT NOT NULL IDENTITY,
    Name NVARCHAR(50),
    UnitPrice FLOAT,
    Discount FLOAT,
    Image NVARCHAR(50),
    Available BIT,
    CategoryId BIGINT,
    Disable BIT NOT NULL,
    PRIMARY KEY(Id),
    FOREIGN KEY(CategoryId) REFERENCES Categories(Id) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
)

CREATE TABLE Cards(
    Id BIGINT NOT NULL IDENTITY,
    Status INT,
    Disable BIT NOT NULL,
    PRIMARY KEY(Id)
)

CREATE TABLE Users(
    Username NVARCHAR(20) NOT NULL,
    Password NVARCHAR(50) NOT NULL,
    Enabled BIT,
    Fullname NVARCHAR(50),
    Photo NVARCHAR(50),
    Manager BIT,
    Disable BIT NOT NULL,
    PRIMARY KEY(Username)
)

CREATE TABLE Bills(
    Id BIGINT NOT NULL IDENTITY,
    Username NVARCHAR(20) NOT NULL,
    CardId BIGINT,
    Checkin DATETIME,
    Checkout DATETIME,
    Status INT,
    Disable BIT NOT NULL,
    PRIMARY KEY(Id),
    FOREIGN KEY(Username) REFERENCES Users(Username) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE,
    FOREIGN KEY(CardId) REFERENCES Cards(Id) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
)

CREATE TABLE BillDetails(
    Id BIGINT NOT NULL IDENTITY,
    BillId BIGINT NOT NULL,
    DrinkId BIGINT NOT NULL,
    UnitPrice FLOAT,
    Discount FLOAT,
    Quantity INT,
    Disable BIT NOT NULL,
    PRIMARY KEY(Id),
    FOREIGN KEY(BillId) REFERENCES Bills(Id) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE,
    FOREIGN KEY(DrinkId) REFERENCES Drinks(Id) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
)

INSERT INTO Categories (Name, Disable) VALUES 
(N'Coffee', 0),
(N'Tea', 0),
(N'Juice', 0),
(N'Smoothie', 0),
(N'Soda', 0),
(N'Milk', 0),
(N'Ice Cream', 0),
(N'Cake', 0),
(N'Bread', 0),
(N'Snack', 0),
(N'Sandwich', 0),
(N'Pizza', 0),
(N'Burger', 0),
(N'Pasta', 0),
(N'Salad', 0),
(N'Soup', 0),
(N'Hot Drink', 0),
(N'Cold Drink', 0),
(N'Alcohol', 1),
(N'Other', 0);

INSERT INTO Cards (Status, Disable) VALUES
(0, 0),
(1, 0),
(2, 0),
(0, 1),
(1, 1),
(2, 1),
(0, 0),
(1, 0),
(2, 0),
(0, 1),
(1, 1),
(2, 1),
(0, 0),
(1, 0),
(2, 0),
(0, 1),
(1, 1),
(2, 1),
(0, 0),
(1, 0);

INSERT INTO Drinks (Name, UnitPrice, Discount, Image, Available, CategoryId, Disable) VALUES
(N'Espresso',        45000, 0.0,  N'espresso.png',        1, 12, 0),
(N'Cappuccino',      55000, 0.1,  N'cappuccino.png',      1, 5, 0),
(N'Latte',           52000, 0.05, N'latte.png',           1, 8, 0),
(N'Americano',       40000, 0.0,  N'americano.png',       1, 1, 0),
(N'Mocha',           60000, 0.15, N'mocha.png',           1, 14, 0),
(N'Green Tea',       35000, 0.0,  N'greentea.png',        1, 3, 0),
(N'Black Tea',       32000, 0.0,  N'blacktea.png',        1, 6, 0),
(N'Herbal Tea',      36000, 0.0,  N'herbaltea.png',       1, 9, 0),
(N'Orange Juice',    45000, 0.1,  N'orangejuice.png',     1, 17, 0),
(N'Apple Juice',     42000, 0.05, N'applejuice.png',      1, 10, 0),
(N'Mango Smoothie',  60000, 0.2,  N'mangosmoothie.png',   1, 7, 0),
(N'Strawberry Shake',55000, 0.1,  N'strawberry.png',      1, 16, 0),
(N'Cola',            30000, 0.0,  N'cola.png',            1, 4, 0),
(N'Sprite',          30000, 0.0,  N'sprite.png',          1, 11, 0),
(N'Mineral Water',   20000, 0.0,  N'water.png',           1, 19, 0),
(N'Chocolate Milk',  42000, 0.05, N'chocolatemilk.png',   1, 13, 0),
(N'Vanilla Milkshake',52000,0.1,  N'vanillashake.png',    1, 15, 0),
(N'Red Wine',        180000,0.2,  N'redwine.png',         1, 2, 0),
(N'Beer',            45000, 0.1,  N'beer.png',            1, 18, 0),
(N'Whiskey',         250000,0.25, N'whiskey.png',         1, 20, 0),
(N'Caramel Latte',   55000, 0.1,  N'caramellatte.png',    1, 4, 0),
(N'Hazelnut Coffee', 58000, 0.15, N'hazelnutcoffee.png',  1, 10, 0),
(N'Macchiato',       52000, 0.05, N'macchiato.png',       1, 8, 0),
(N'Flat White',      50000, 0.0,  N'flatwhite.png',       1, 5, 0),
(N'Iced Coffee',     48000, 0.1,  N'icedcoffee.png',      1, 13, 0),
(N'Lemon Tea',       35000, 0.0,  N'lemontea.png',        1, 1, 0),
(N'Peach Tea',       37000, 0.05, N'peachtea.png',        1, 9, 0),
(N'Matcha Latte',    60000, 0.1,  N'matchalatte.png',     1, 6, 0),
(N'Pineapple Juice', 42000, 0.05, N'pineapplejuice.png',  1, 3, 0),
(N'Grape Juice',     43000, 0.0,  N'grapejuice.png',      1, 11, 0),
(N'Banana Smoothie', 55000, 0.1,  N'bananasmoothie.png',  1, 2, 0),
(N'Blueberry Smoothie',62000,0.15, N'blueberry.png',      1, 15, 0),
(N'Fanta',           32000, 0.0,  N'fanta.png',           1, 17, 0),
(N'Tonic Water',     33000, 0.0,  N'tonicwater.png',      1, 7, 0),
(N'Coconut Water',   40000, 0.0,  N'coconutwater.png',    1, 12, 0),
(N'Almond Milk',     45000, 0.05, N'almondmilk.png',      1, 14, 0),
(N'Oreo Milkshake',  58000, 0.1,  N'oreo.png',            1, 18, 0),
(N'White Wine',      190000,0.2,  N'whitewine.png',       1, 19, 0),
(N'Gin Tonic',       230000,0.25, N'gin.png',             1, 16, 0),
(N'Vodka',           250000,0.3,  N'vodka.png',           1, 20, 0);

INSERT INTO Users (Username, Password, Enabled, Fullname, Photo, Manager, Disable) VALUES
(N'user001', N'pass001', 1, N'Nguyen Van A', N'photo.png', 0, 0),
(N'user002', N'pass002', 1, N'Tran Thi B', N'photo.png', 0, 0),
(N'user003', N'pass003', 1, N'Le Van C', N'photo.png', 0, 0),
(N'user004', N'pass004', 1, N'Pham Thi D', N'photo.png', 0, 0),
(N'user005', N'pass005', 1, N'Hoang Van E', N'photo.png', 1, 0),
(N'user006', N'pass006', 1, N'Nguyen Thi F', N'photo.png', 0, 0),
(N'user007', N'pass007', 1, N'Vu Van G', N'photo.png', 0, 0),
(N'user008', N'pass008', 1, N'Pham Thi H', N'photo.png', 0, 0),
(N'user009', N'pass009', 1, N'Do Van I', N'photo.png', 0, 0),
(N'user010', N'pass010', 1, N'Ngo Thi J', N'photo.png', 0, 0),
(N'user011', N'pass011', 1, N'Nguyen Van K', N'photo.png', 0, 0),
(N'user012', N'pass012', 1, N'Tran Thi L', N'photo.png', 0, 0),
(N'user013', N'pass013', 1, N'Le Van M', N'photo.png', 0, 0),
(N'user014', N'pass014', 1, N'Pham Thi N', N'photo.png', 0, 0),
(N'user015', N'pass015', 1, N'Hoang Van O', N'photo.png', 1, 0),
(N'user016', N'pass016', 1, N'Nguyen Thi P', N'photo.png', 0, 0),
(N'user017', N'pass017', 1, N'Vu Van Q', N'photo.png', 0, 0),
(N'user018', N'pass018', 1, N'Pham Thi R', N'photo.png', 0, 0),
(N'user019', N'pass019', 1, N'Do Van S', N'photo.png', 0, 0),
(N'user020', N'pass020', 1, N'Ngo Thi T', N'photo.png', 0, 0);

INSERT INTO Bills (Username, CardId, Checkin, Checkout, Status, Disable) VALUES
('user001', 1, '2025-10-01 08:30:00', '2025-10-01 09:15:00', 1, 0),
('user002', 2, '2025-10-01 09:00:00', '2025-10-01 09:45:00', 1, 0),
('user003', 3, '2025-10-02 10:00:00', '2025-10-02 10:30:00', 1, 0),
('user004', 4, '2025-10-02 11:00:00', '2025-10-02 11:50:00', 1, 0),
('user005', 5, '2025-10-03 07:45:00', '2025-10-03 08:20:00', 1, 0),
('user006', 6, '2025-10-03 09:10:00', '2025-10-03 09:50:00', 1, 0),
('user007', 7, '2025-10-04 08:00:00', '2025-10-04 08:30:00', 1, 0),
('user008', 8, '2025-10-04 09:00:00', '2025-10-04 09:40:00', 1, 0),
('user009', 9, '2025-10-05 10:15:00', '2025-10-05 10:45:00', 1, 0),
('user010', 10, '2025-10-05 11:20:00', '2025-10-05 11:55:00', 1, 0),
('user011', 11, '2025-10-06 08:25:00', '2025-10-06 09:00:00', 1, 0),
('user012', 12, '2025-10-06 09:30:00', '2025-10-06 10:10:00', 1, 0),
('user013', 13, '2025-10-07 07:50:00', '2025-10-07 08:30:00', 1, 0),
('user014', 14, '2025-10-07 09:15:00', '2025-10-07 09:55:00', 1, 0),
('user015', 15, '2025-10-08 08:10:00', '2025-10-08 08:40:00', 1, 0),
('user016', 16, '2025-10-08 09:45:00', '2025-10-08 10:20:00', 1, 0),
('user017', 17, '2025-10-09 08:00:00', '2025-10-09 08:50:00', 1, 0),
('user018', 18, '2025-10-09 09:10:00', '2025-10-09 09:40:00', 1, 0),
('user019', 19, '2025-10-10 10:30:00', '2025-10-10 11:00:00', 1, 0),
('user020', 20, '2025-10-10 11:15:00', '2025-10-10 11:45:00', 1, 0);

INSERT INTO BillDetails (BillId, DrinkId, UnitPrice, Discount, Quantity, Disable) VALUES
(1,  1,  45000, 0.0,  2, 0),   -- Espresso
(2,  5,  60000, 0.15, 1, 0),   -- Mocha
(3, 10,  42000, 0.05, 3, 0),   -- Apple Juice
(4,  7,  32000, 0.0,  2, 0),   -- Black Tea
(5,  3,  52000, 0.05, 1, 0),   -- Latte
(6, 11,  60000, 0.2,  2, 0),   -- Mango Smoothie
(7, 15,  20000, 0.0,  3, 0),   -- Mineral Water
(8, 18,  45000, 0.1,  1, 0),   -- Beer
(9, 13,  30000, 0.0,  2, 0),   -- Cola
(10, 8,  36000, 0.0,  1, 0),   -- Herbal Tea
(11, 4,  40000, 0.0,  2, 0),   -- Americano
(12, 20, 250000,0.25, 1, 0),   -- Whiskey
(13, 25, 50000, 0.0,  1, 0),   -- Flat White
(14, 27, 35000, 0.0,  2, 0),   -- Lemon Tea
(15, 30, 42000, 0.05, 1, 0),   -- Pineapple Juice
(16, 33, 55000, 0.1,  2, 0),   -- Banana Smoothie
(17, 36, 40000, 0.0,  1, 0),   -- Coconut Water
(18, 37, 45000, 0.05, 2, 0),   -- Almond Milk
(19, 38, 58000, 0.1,  1, 0),   -- Oreo Milkshake
(20, 39, 190000,0.2,  1, 0),   -- White Wine
(1,  2,  55000, 0.1,  1, 0),   -- Cappuccino
(2,  6,  35000, 0.0,  2, 0),   -- Green Tea
(3,  9,  45000, 0.1,  1, 0),   -- Orange Juice
(4, 12,  55000, 0.1,  1, 0),   -- Strawberry Shake
(5, 14,  30000, 0.0,  1, 0),   -- Sprite
(6, 16,  42000, 0.05, 2, 0),   -- Chocolate Milk
(7, 17,  52000, 0.1,  1, 0),   -- Vanilla Milkshake
(8, 19, 180000,0.2,  1, 0),    -- Red Wine
(9, 21,  55000, 0.1,  2, 0),   -- Caramel Latte
(10,22,  58000, 0.15, 1, 0),   -- Hazelnut Coffee
(11,23,  52000, 0.05, 2, 0),   -- Macchiato
(12,24,  50000, 0.0,  1, 0),   -- Flat White
(13,26,  48000, 0.1,  2, 0),   -- Iced Coffee
(14,28,  37000, 0.05, 1, 0),   -- Peach Tea
(15,29,  60000, 0.1,  1, 0),   -- Matcha Latte
(16,31,  43000, 0.0,  2, 0),   -- Grape Juice
(17,32,  55000, 0.1,  1, 0),   -- Banana Smoothie
(18,34,  33000, 0.0,  2, 0),   -- Tonic Water
(19,35,  40000, 0.0,  1, 0),   -- Coconut Water
(20,40, 230000,0.25, 1, 0),    -- Gin Tonic
(1,  3,  52000, 0.05, 1, 0),   -- Latte
(2, 13, 30000, 0.0,  2, 0),    -- Cola
(3, 18, 45000, 0.1,  1, 0),    -- Beer
(4, 20, 250000,0.25, 1, 0),    -- Whiskey
(5, 33, 55000, 0.1,  2, 0),    -- Banana Smoothie
(6, 38, 58000, 0.1,  1, 0),    -- Oreo Milkshake
(7, 39, 190000,0.2,  1, 0),    -- White Wine
(8, 30, 42000, 0.05, 1, 0),    -- Pineapple Juice
(9, 32, 55000, 0.1,  2, 0),    -- Banana Smoothie
(10,15, 20000, 0.0,  3, 0),    -- Mineral Water
(11,24, 50000, 0.0,  1, 0),    -- Flat White
(12,28, 37000, 0.05, 1, 0),    -- Peach Tea
(13,25, 50000, 0.0,  1, 0),    -- Flat White
(14,26, 48000, 0.1,  1, 0),    -- Iced Coffee
(15,31, 43000, 0.0,  1, 0),    -- Grape Juice
(16,36, 40000, 0.0,  2, 0),    -- Coconut Water
(17,37, 45000, 0.05, 1, 0),    -- Almond Milk
(18,19,180000,0.2,  1, 0),     -- Red Wine
(19,22,58000, 0.15, 2, 0),     -- Hazelnut Coffee
(20,40,230000,0.25, 1, 0);     -- Gin Tonic


USE master;
GO

ALTER DATABASE coffeeshopmanager SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
GO

DROP DATABASE coffeeshopmanager;
GO