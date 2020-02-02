insert into CATEGORY(id, name)
values (1, 'Other');
insert into CATEGORY(id, name)
values (2, 'Laptop');
insert into CATEGORY(id, name)
values (3, 'Tablet');
insert into CATEGORY(id, name)
values (4, 'Components');
insert into CATEGORY(id, name)
values (5, 'Accessories');
insert into CATEGORY(id, name)
values (6, 'Desktop');

insert into ROLE (id, name)
values (1, 'GUEST');
insert into ROLE (id, name)
values (2, 'USER');
insert into ROLE (id, name)
values (3, 'ADMIN');

insert into User (id, first_name, last_name, email, password, gender, phone, modified_at, created_at, avatar_url)
values (1, 'Lana', 'Drahrepus', 'admin@mail.com', '123456', 'F', '01234567890', '2020-01-30 11:23:39',
        '2020-01-30 11:23:39', null);

insert into User_Role(user_id, role_id)
values (1, 3);
