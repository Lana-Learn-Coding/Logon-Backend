INSERT INTO CATEGORY(id, name)
VALUES (1, 'Other');
INSERT INTO CATEGORY(id, name)
VALUES (2, 'Laptop');
INSERT INTO CATEGORY(id, name)
VALUES (3, 'Tablet');
INSERT INTO CATEGORY(id, name)
VALUES (4, 'Components');
INSERT INTO CATEGORY(id, name)
VALUES (5, 'Accessories');
INSERT INTO CATEGORY(id, name)
VALUES (6, 'Desktop');

INSERT INTO ROLE (id, name)
VALUES (1, 'ROLE_GUEST');
INSERT INTO ROLE (id, name)
VALUES (2, 'ROLE_USER');
INSERT INTO ROLE (id, name)
VALUES (3, 'ROLE_ADMIN');

INSERT INTO User (id, first_name, last_name, email, password, gender, phone, modified_at, created_at, avatar_url)
VALUES (1, 'Lana', 'Drahrepus', 'admin@mail.com', '123456', 'F', '01234567890', '2020-01-30 11:23:39',
        '2020-01-30 11:23:39', NULL);

insert into User_Role(user_id, role_id)
values (1, 3);
