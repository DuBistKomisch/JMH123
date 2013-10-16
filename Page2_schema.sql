DROP TABLE HomeLoanPage2.Page2;

DROP SCHEMA HomeLoanPage2 RESTRICT;

CREATE SCHEMA HomeLoanPage2;

CREATE TABLE HomeLoanPage2.Page2(
    ID INTEGER NOT NULL PRIMARY KEY
    GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    c_ID INTEGER NOT NULL,
    currentJob varchar(255) NOT NULL,
    salary double NOT NULL
);