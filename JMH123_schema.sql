DROP TABLE JMH123.Employee;
DROP TABLE JMH123.CUSTOMER;
DROP TABLE JMH123.SAVINGS;
DROP TABLE JMH123.Transactions;

DROP SCHEMA JMH123 RESTRICT;

CREATE SCHEMA JMH123;

CREATE TABLE JMH123.Employee(
E_ID varchar(5), E_FirstName varchar(255),
E_LastName varchar(255), E_Password varchar(255)
);

CREATE TABLE JMH123.Customer(
C_ID varchar(5), C_FirstName varchar(255),
C_LastName varchar(255), DOB date, Address varchar (255)
);

CREATE TABLE JMH123.Savings(
C_ID varchar(5), AccNum varchar(255), Balance decimal(6,2),
S_DateTimeCreated timestamp
);

CREATE TABLE JMH123.Transactions(
T_ID varchar(5), AccNum varchar(255), Amount decimal(6,2),
Description varchar(255), AccNum2 varchar(255), E_ID varchar(5),
T_DateTimeCreated timestamp
);