
/*DROP TABLE JMH123.Transactions;
DROP TABLE JMH123.Employees;
DROP TABLE JMH123.Savings;
DROP TABLE JMH123.Customers;

DROP SCHEMA JMH123 RESTRICT;
*/

CREATE SCHEMA JMH123;

CREATE TABLE JMH123.Employees (
    E_ID varchar(5) NOT null PRIMARY KEY,
    E_FirstName varchar(255),
    E_LastName varchar(255),
    E_Password varchar(255)
);

CREATE TABLE JMH123.Customers (
    C_ID varchar(5) NOT null PRIMARY KEY,
    C_FirstName varchar(255),
    C_LastName varchar(255),
    DOB date,
    Address varchar(255)
);

CREATE TABLE JMH123.Savings (
    C_ID varchar(5),
    AccNum varchar(255) NOT null PRIMARY KEY,
    Balance decimal(6,2),
    S_DateTimeCreated timestamp,
    FOREIGN KEY (C_ID) REFERENCES JMH123.Customers (C_ID)
);

CREATE TABLE JMH123.Transactions (
    T_ID varchar(5) Not null PRIMARY KEY,
    AccNum varchar(255),
    Amount decimal(6,2),
    Description varchar(255),
    AccNum2 varchar(255),
    E_ID varchar(5),
    T_DateTimeCreated timestamp,
    FOREIGN KEY (E_ID) REFERENCES JMH123.Employees (E_ID),
    FOREIGN KEY (AccNum) REFERENCES JMH123.Savings (AccNum)
);
