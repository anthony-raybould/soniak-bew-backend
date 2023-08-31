-- Employees
CREATE TABLE IF NOT EXISTS
Employees (
    EmployeeID              smallint unsigned AUTO_INCREMENT NOT NULL,
    Name                    varchar(70) NOT NULL,
    Salary                  decimal(10,2) NOT NULL,
    BankAccountNumber       varchar(8) NOT NULL,
    NationalInsuranceNumber char(9) NOT NULL,
    PRIMARY KEY (EmployeeID)
);

CREATE TABLE IF NOT EXISTS
SalesEmployees (
    EmployeeID     smallint unsigned NOT NULL,
    CommissionRate double NOT NULL,
    FOREIGN KEY (EmployeeID) REFERENCES Employees(EmployeeID),
    PRIMARY KEY (EmployeeID)
);

CREATE TABLE IF NOT EXISTS
DeliveryEmployees (
    EmployeeID smallint unsigned NOT NULL,
    FOREIGN KEY (EmployeeID) REFERENCES Employees(EmployeeID),
    PRIMARY KEY (EmployeeID)
);


-- Clients
CREATE TABLE IF NOT EXISTS
Clients (
	ClientID    smallint unsigned AUTO_INCREMENT NOT NULL,
    Name        varchar(70) NOT NULL,
    Address     varchar(255) NOT NULL,
    PhoneNumber char(11) NOT NULL,
    EmployeeID  smallint unsigned NOT NULL,
    FOREIGN KEY (EmployeeID) REFERENCES SalesEmployees(EmployeeID),
    PRIMARY KEY (ClientID)
);


-- Projects
CREATE TABLE IF NOT EXISTS
Projects (
	ProjectID smallint unsigned AUTO_INCREMENT,
    Name      varchar(100) NOT NULL,
    Value     decimal(15,2) NOT NULL,
    TechLead  smallint unsigned NOT NULL,
    ClientID  smallint unsigned NOT NULL,
    StartDate date,
    EndDate   date,
    FOREIGN KEY (TechLead) REFERENCES DeliveryEmployees(EmployeeId),
    FOREIGN KEY (ClientID) REFERENCES Clients(ClientID),
    PRIMARY KEY (ProjectID)
);

CREATE TABLE IF NOT EXISTS
Technologies (
	TechnologiesID smallint UNSIGNED AUTO_INCREMENT,
    Name           varchar(100) NOT NULL,
    Description    varchar(255) NOT NULL,
    PRIMARY KEY (TechnologiesID)
);

CREATE TABLE IF NOT EXISTS
Projects_Technologies (
    ProjectID      smallint unsigned NOT NULL,
    TechnologiesID smallint unsigned NOT NULL,
    FOREIGN KEY (ProjectID) REFERENCES Projects(ProjectID),
	FOREIGN KEY (TechnologiesID) REFERENCES Technologies(TechnologiesID),
	PRIMARY KEY (ProjectID, TechnologiesID)
);

CREATE TABLE IF NOT EXISTS
DeliveryEmployees_Projects (
    EmployeeID smallint unsigned NOT NULL,
    ProjectID  smallint unsigned NOT NULL,
    FOREIGN KEY (EmployeeID) REFERENCES DeliveryEmployees(EmployeeID),
    FOREIGN KEY (ProjectID) REFERENCES Projects(ProjectID),
    PRIMARY KEY (EmployeeID, ProjectID)
);
