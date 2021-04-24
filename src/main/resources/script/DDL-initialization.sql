-- CREATE SEQUENCE USER_ID_SEQ_GEN START WITH 1 INCREMENT BY 1 CACHE 10 NOCYCLE;
-- CREATE TABLE USERS(ID INTEGER AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(50) NOT NULL, EMAIL VARCHAR(50) NOT NULL);
-- CREATE TABLE AUTHENTICATES(LOGIN VARCHAR(50) NOT NULL, PASSWORD VARCHAR(50) NOT NULL, USER_ID INTEGER PRIMARY KEY NOT NULL);
-- CREATE SEQUENCE PRODUCT_ID_SEQ_GEN START WITH 1 INCREMENT BY 1 CACHE 10 NOCYCLE;
-- CREATE TABLE PRODUCTS(ID INTEGER NOT NULL, NAME VARCHAR(50) NOT NULL, DESCRIPTION VARCHAR(255) NOT NULL, PRICE DOUBLE NOT NULL, ENABLED BOOLEAN NOT NULL, USER_ID INTEGER);
-- CREATE SEQUENCE ROLE_ID_SEQ_GEN START WITH 1 INCREMENT BY 1 CACHE 10 NOCYCLE;
-- CREATE TABLE ROLES(ID INTEGER AUTO_INCREMENT PRIMARY KEY, ROLE VARCHAR(50) NOT NULL, USER_ID INTEGER NOT NULL);
-- CREATE TABLE ROLE_USER(ROLE_ID INTEGER NOT NULL, USER_ID INTEGER NOT NULL);

CREATE TABLE AUTHORS (ID int PRIMARY KEY AUTO_INCREMENT, AUTHOR varchar(50) NOT NULL UNIQUE);

CREATE TABLE GENRES ( ID int PRIMARY KEY AUTO_INCREMENT, GENRE varchar(20) NOT NULL UNIQUE);

CREATE TABLE ROLES (ID int AUTO_INCREMENT, ROLE varchar(20) NOT NULL UNIQUE, PRIMARY KEY (ID));

CREATE TABLE STATUSES (ID int AUTO_INCREMENT, STATUS varchar(20) NOT NULL, PRIMARY KEY (ID));

CREATE TABLE USERS (ID int PRIMARY KEY AUTO_INCREMENT, NAME varchar(50) NOT NULL, EMAIL VARCHAR(50) NOT NULL, STATUSES_ID INTEGER NOT NULL, FOREIGN KEY (STATUSES_ID) REFERENCES STATUSES(ID));

CREATE TABLE USERS_ROLES (USERS_ID INTEGER NOT NULL, ROLES_ID INTEGER NOT NULL, PRIMARY KEY (ROLES_ID, USERS_ID), FOREIGN KEY (USERS_ID) REFERENCES USERS(ID), FOREIGN KEY (ROLES_ID) REFERENCES ROLES(ID));

CREATE TABLE BOOKS (ID int NOT NULL AUTO_INCREMENT, TITLE varchar(63) NOT NULL, DESCRIPTION varchar(255), IMAGE BLOB, QUANTITY int NOT NULL, AUTHORS_ID int NOT NULL, PRIMARY KEY (ID), FOREIGN KEY (AUTHORS_ID) REFERENCES AUTHORS(ID));

CREATE TABLE BOOKS_GENRES (BOOKS_ID INTEGER NOT NULL, GENRES_ID INTEGER NOT NULL, PRIMARY KEY (BOOKS_ID, GENRES_ID), FOREIGN KEY (BOOKS_ID) REFERENCES BOOKS(ID), FOREIGN KEY (GENRES_ID) REFERENCES GENRES(ID));

CREATE TABLE MESSAGES (ID int NOT NULL AUTO_INCREMENT, DATE_SENT date NOT NULL, CONTENT varchar(255), USERS_ID int, PRIMARY KEY (ID), FOREIGN KEY (USERS_ID) REFERENCES USERS (ID));

CREATE TABLE ORDERS (ID int NOT NULL AUTO_INCREMENT, DATE_RECEIVING date NOT NULL, DURATION int NOT NULL, BOOKS_ID int NOT NULL, USERS_ID int NOT NULL, PRIMARY KEY (ID), FOREIGN KEY (BOOKS_ID) REFERENCES BOOKS (ID), FOREIGN KEY (USERS_ID) REFERENCES USERS (ID));

CREATE TABLE AUTHENTICATES(LOGIN VARCHAR(40) UNIQUE NOT NULL, PASSWORD VARCHAR(40) NOT NULL, UNLOCKED_DATE DATE NOT NULL, USER_ID INTEGER PRIMARY KEY, FOREIGN KEY (USER_ID) REFERENCES USERS(ID));