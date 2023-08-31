INSERT INTO Employees (Name, Salary, BankAccountNumber, NationalInsuranceNumber)
VALUES
    -- Delivery
    ('John Doe', 50000.00, '12345678', 'ABC123XYZ'),
    ('Jane Smith', 55000.00, '98765432', 'DEF456UVW'),
    ('Michael Johnson', 48000.00, '56789012', 'GHI789RST'),
    ('Emily Brown', 52000.00, '34567890', 'JKL012ABC'),
    ('David Wilson', 60000.00, '87654321', 'MNO345DEF'),
    ('Sarah Lee', 53000.00, '23456789', 'PQR678GHI'),
    ('Daniel Clark', 49000.00, '76543210', 'STU901JKL'),
    ('Jessica Taylor', 58000.00, '45678901', 'VWX234MNO'),
    ('Kevin Martinez', 51000.00, '65432109', 'YZA567PQR'),
    ('Linda Anderson', 54000.00, '34567812', 'BCD890STU'),
    -- Sales
    ('John Smith', 50000.00, '12345678', 'AB123456C'),
    ('Jane Doe', 55000.00, '87654321', 'CD987654A'),
    ('Michael Johnson', 60000.00, '45678901', 'EF234567B'),
    ('Emily Williams', 52000.00, '56789012', 'GH345678D'),
    ('Robert Brown', 48000.00, '23456789', 'IJ456789E'),
    ('Jessica Taylor', 53000.00, '34567890', 'KL567890F'),
    ('David Lee', 58000.00, '67890123', 'MN678901G'),
    ('Sarah Anderson', 51000.00, '78901234', 'OP789012H'),
    ('Christopher Martinez', 57000.00, '89012345', 'QR890123I'),
    ('Linda Wilson', 54000.00, '90123456', 'ST901234J');
    
INSERT INTO DeliveryEmployees (EmployeeID)
VALUES
    (1),
    (2),
    (3),
    (4),
    (5),
    (6),
    (7),
    (8),
    (9),
    (10);

INSERT INTO SalesEmployees (EmployeeID, CommissionRate)
VALUES
    (11, 0.10),
    (12, 0.12),
    (13, 0.08),
    (14, 0.15),
    (15, 0.09),
    (16, 0.11),
    (17, 0.07),
    (18, 0.13),
    (19, 0.10),
    (20, 0.14);

INSERT INTO Clients (Name, Address, PhoneNumber, EmployeeID) VALUES
    ('John Doe', '123 Main St, City A', '555-123411', 11),
    ('Jane Smith', '456 Elm St, City B', '555-567811', 12),
    ('Michael Johnson', '789 Oak St, City C', '555-119876', 13),
    ('Sarah Williams', '234 Maple St, City D', '555-543211', 12),
    ('David Lee', '567 Pine St, City E', '555-876115', 18),
    ('Emily Brown', '890 Birch St, City F', '555-112345', 18),
    ('Daniel Martinez', '123 Cedar St, City G', '11555-6543', 18),
    ('Olivia Taylor', '456 Walnut St, City H', '51155-3456', 19),
    ('James Anderson', '789 Willow St, City I', '11555-7654', 11),
    ('Sophia Wilson', '234 Cherry St, City J', '51155-4321', 20);

INSERT INTO Projects (Name, Value, TechLead, ClientID)
VALUES
    ('Project 1', 50000.00, 1, 1),
    ('Project 2', 75000.00, 2, 2),
    ('Project 3', 120000.00, 3, 3),
    ('Project 4', 90000.00, 4, 1),
    ('Project 5', 60000.00, 2, 4),
    ('Project 6', 80000.00, 5, 2),
    ('Project 7', 95000.00, 3, 3),
    ('Project 8', 110000.00, 1, 4),
    ('Project 9', 70000.00, 4, 1),
    ('Project 10', 85000.00, 5, 3);

INSERT INTO DeliveryEmployees_Projects (EmployeeID, ProjectID)
VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6),
    (7, 7),
    (8, 8),
    (9, 9),
    (10, 10);

INSERT INTO Users (Username, Password, RoleID)
VALUES
    --      Password: 'test'
    ('testSuperuser', '$2a$10$2ZIWyQ.XypgCWtAwZuvQ8eFrB22axyQ5AViXFohbQkx4OSNwAo1Pe', 0),
    ('testHr', '$2a$10$kYDeuiP/Dj.FQoLC.frLD.01cDP97oXrddu9XsCkV1raROA7J4FjO', 1),
    ('testManagement', '$2a$10$kYDeuiP/Dj.FQoLC.frLD.01cDP97oXrddu9XsCkV1raROA7J4FjO', 2),
    ('testSales', '$2a$10$NcnW9mZZFPjvHeyhFvRRX.qphy6e5GLhiifSUFJcWFcxQJl9/bfNK', 3)
