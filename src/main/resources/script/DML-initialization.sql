INSERT INTO ROLES (ROLE) VALUES 'Administrator';
INSERT INTO ROLES (ROLE) VALUES 'User';
INSERT INTO ROLES (ROLE) VALUES 'Guest';


INSERT INTO STATUSES (STATUS) VALUES ('OK');
INSERT INTO STATUSES (STATUS) VALUES ('Limited');
INSERT INTO STATUSES (STATUS) VALUES ('Locked');


INSERT INTO AUTHORS (AUTHOR) VALUES 'Брюс Эккель';
INSERT INTO AUTHORS (AUTHOR) VALUES 'Терри Пратчетт';
INSERT INTO AUTHORS (AUTHOR) VALUES 'Стивен Кинг';
INSERT INTO AUTHORS (AUTHOR) VALUES 'Николай Гоголь';
INSERT INTO AUTHORS (AUTHOR) VALUES 'Александр Дюма';


INSERT INTO GENRES (GENRE) VALUES 'Образовательная';
INSERT INTO GENRES (GENRE) VALUES 'Фентази';
INSERT INTO GENRES (GENRE) VALUES 'Фантастика';
INSERT INTO GENRES (GENRE) VALUES 'Комедия';
INSERT INTO GENRES (GENRE) VALUES 'Приключения';


INSERT INTO USERS (NAME, EMAIL, STATUSES_ID) VALUES ('admin', 'admin@mail.ru', 1);
INSERT INTO AUTHENTICATES (LOGIN, PASSWORD, UNLOCKED_DATE, USER_ID) VALUES ('admin', '1', CURRENT_DATE(), 1);

INSERT INTO USERS (NAME, EMAIL, STATUSES_ID) VALUES ('Иван', 'ivan@mail.ru', 1);
INSERT INTO AUTHENTICATES (LOGIN, PASSWORD, UNLOCKED_DATE, USER_ID) VALUES ('ivan', '1', CURRENT_DATE(), 2);

INSERT INTO USERS (NAME, EMAIL, STATUSES_ID) VALUES ('user', 'user@mail.ru', 1);
INSERT INTO AUTHENTICATES (LOGIN, PASSWORD, UNLOCKED_DATE, USER_ID) VALUES ('user', '1', CURRENT_DATE(), 3);

INSERT INTO USERS (NAME, EMAIL, STATUSES_ID) VALUES ('limit', 'qwe@mail.ru', 2);
INSERT INTO AUTHENTICATES (LOGIN, PASSWORD, UNLOCKED_DATE, USER_ID) VALUES ('qwe', '1', DATEADD('DAY', -1, CURRENT_DATE()), 4);

INSERT INTO USERS (NAME, EMAIL, STATUSES_ID) VALUES ('lock', 'zak@mail.ru', 3);
INSERT INTO AUTHENTICATES (LOGIN, PASSWORD, UNLOCKED_DATE, USER_ID) VALUES ('zak', '1', CURRENT_DATE(), 5);

INSERT INTO MESSAGES (DATE_SENT, CONTENT, USERS_ID) VALUES ('2020-12-15', 'unlock me', 5);
INSERT INTO MESSAGES (DATE_SENT, CONTENT, USERS_ID) VALUES (CURRENT_DATE(), 'plz', 5);
INSERT INTO MESSAGES (DATE_SENT, CONTENT, USERS_ID) VALUES (CURRENT_DATE(), 'Hello!', 2);


INSERT INTO BOOKS (TITLE, DESCRIPTION, QUANTITY, AUTHORS_ID) VALUES ('Философия Java', 'Книго по програмированию на Java', 0, 1);
INSERT INTO BOOKS (TITLE, DESCRIPTION, QUANTITY, AUTHORS_ID) VALUES ('Незримые академики', 'Еще одна история аля "Ромео и Джульета"... Наверное...', 5, 2);
INSERT INTO BOOKS (TITLE, DESCRIPTION, QUANTITY, AUTHORS_ID) VALUES ('11/22/63', 'Путешествие во времени, любовь и прочие парадоксы', 5, 3);
INSERT INTO BOOKS (TITLE, DESCRIPTION, QUANTITY, AUTHORS_ID) VALUES ('Как писать книги', 'Об мастерстве от мастера', 1, 3);
INSERT INTO BOOKS (TITLE, DESCRIPTION, QUANTITY, AUTHORS_ID) VALUES ('Темная башня', 'Заключителья книга из одноименной серии', 5, 3);
INSERT INTO BOOKS (TITLE, DESCRIPTION, QUANTITY, AUTHORS_ID) VALUES ('Стража! Стража!', 'О тяжелых буднях САМЫХ обычных стражников', 5, 2);
INSERT INTO BOOKS (TITLE, DESCRIPTION, QUANTITY, AUTHORS_ID) VALUES ('Граф Монте-Кристо', 'Не имей сто друзей, а имей небольшой оставок с сундуком золота', 5, 5);
INSERT INTO BOOKS (TITLE, DESCRIPTION, QUANTITY, AUTHORS_ID) VALUES ('Держи марку!', 'Хотите расскажу вам два занимательных факта про ангелов?', 4, 2);
INSERT INTO BOOKS (TITLE, DESCRIPTION, QUANTITY, AUTHORS_ID) VALUES ('Ревизор', 'Комедия в пяти действиях', 3, 4);


INSERT INTO BOOKS_GENRES (BOOKS_ID, GENRES_ID) VALUES (1, 1);
INSERT INTO BOOKS_GENRES (BOOKS_ID, GENRES_ID) VALUES (2, 2);
INSERT INTO BOOKS_GENRES (BOOKS_ID, GENRES_ID) VALUES (2, 4);
INSERT INTO BOOKS_GENRES (BOOKS_ID, GENRES_ID) VALUES (3, 3);
INSERT INTO BOOKS_GENRES (BOOKS_ID, GENRES_ID) VALUES (3, 5);
INSERT INTO BOOKS_GENRES (BOOKS_ID, GENRES_ID) VALUES (4, 1);
INSERT INTO BOOKS_GENRES (BOOKS_ID, GENRES_ID) VALUES (5, 2);
INSERT INTO BOOKS_GENRES (BOOKS_ID, GENRES_ID) VALUES (5, 3);
INSERT INTO BOOKS_GENRES (BOOKS_ID, GENRES_ID) VALUES (5, 5);
INSERT INTO BOOKS_GENRES (BOOKS_ID, GENRES_ID) VALUES (6, 2);
INSERT INTO BOOKS_GENRES (BOOKS_ID, GENRES_ID) VALUES (6, 4);
INSERT INTO BOOKS_GENRES (BOOKS_ID, GENRES_ID) VALUES (6, 5);
INSERT INTO BOOKS_GENRES (BOOKS_ID, GENRES_ID) VALUES (7, 5);
INSERT INTO BOOKS_GENRES (BOOKS_ID, GENRES_ID) VALUES (8, 2);
INSERT INTO BOOKS_GENRES (BOOKS_ID, GENRES_ID) VALUES (8, 4);
INSERT INTO BOOKS_GENRES (BOOKS_ID, GENRES_ID) VALUES (8, 5);
INSERT INTO BOOKS_GENRES (BOOKS_ID, GENRES_ID) VALUES (9, 4);


INSERT INTO USERS_ROLES (USERS_ID, ROLES_ID) VALUES (1, 1);
INSERT INTO USERS_ROLES (USERS_ID, ROLES_ID) VALUES (1, 2);
INSERT INTO USERS_ROLES (USERS_ID, ROLES_ID) VALUES (2, 1);
INSERT INTO USERS_ROLES (USERS_ID, ROLES_ID) VALUES (2, 2);
INSERT INTO USERS_ROLES (USERS_ID, ROLES_ID) VALUES (3, 2);
INSERT INTO USERS_ROLES (USERS_ID, ROLES_ID) VALUES (4, 2);
INSERT INTO USERS_ROLES (USERS_ID, ROLES_ID) VALUES (5, 2);


INSERT INTO ORDERS (DATE_RECEIVING, DURATION, BOOKS_ID, USERS_ID) VALUES (CURRENT_DATE(), 30, 2, 1);
INSERT INTO ORDERS (DATE_RECEIVING, DURATION, BOOKS_ID, USERS_ID) VALUES (CURRENT_DATE(), 5, 3, 3);
INSERT INTO ORDERS (DATE_RECEIVING, DURATION, BOOKS_ID, USERS_ID) VALUES ('2021-01-16', 1, 4, 3);
INSERT INTO ORDERS (DATE_RECEIVING, DURATION, BOOKS_ID, USERS_ID) VALUES ('2021-01-01', 85, 4, 4);
INSERT INTO ORDERS (DATE_RECEIVING, DURATION, BOOKS_ID, USERS_ID) VALUES ('2021-01-20', 45, 5, 4);
INSERT INTO ORDERS (DATE_RECEIVING, DURATION, BOOKS_ID, USERS_ID) VALUES ('2020-12-15', 20, 6, 4);
INSERT INTO ORDERS (DATE_RECEIVING, DURATION, BOOKS_ID, USERS_ID) VALUES ('2020-12-15', 20, 7, 4);
INSERT INTO ORDERS (DATE_RECEIVING, DURATION, BOOKS_ID, USERS_ID) VALUES ('2020-12-15', 20, 8, 4);
INSERT INTO ORDERS (DATE_RECEIVING, DURATION, BOOKS_ID, USERS_ID) VALUES ('2020-12-15', 20, 9, 4);



