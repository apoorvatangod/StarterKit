insert into book (id, title, authors, status) values (1, 'First book', 'Jan Kowalski', 'FREE');
insert into book (id, title, authors, status) values (2, 'Second book', 'Zbigniew Nowak', 'FREE');
insert into book (id, title, authors, status) values (3, 'Third book', 'Janusz Jankowski', 'FREE');

insert into userentity (id, user_name, password) values (1, 'user', 'user');
insert into userentity (id, user_name, password) values (2, 'admin', 'admin');
insert into userentity (id, user_name, password) values (3, 'librarian', 'librarian');

insert into roleentity (id, user_name, user_role) values (1, 'user', 'ROLE_USER');
insert into roleentity (id, user_name, user_role) values (2, 'admin', 'ROLE_ADMIN');
insert into roleentity (id, user_name, user_role) values (3, 'librarian', 'ROLE_LIBRARIAN');