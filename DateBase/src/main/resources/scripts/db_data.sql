-- use company database

USE company;

-- insert data into departments table

INSERT INTO departments (name) VALUES ('IT Department'), ('HR Department'), ('PM Office'), ('Operations Department'), ('Bussiness Department'), ('PR Department');

-- insert data into employees table

INSERT INTO employees (first_name, last_name, pesel, birth_date, department_id) VALUES('Janek', 'Kowalski', '00000000000',DATE '2000-01-01', 1), 
('Zdzichu', 'Malinowski', '00000000001',DATE '2000-01-02', 1), ('Ala', 'Kot', '00000000002',DATE '1990-01-02', 1), ('Piotr', 'Abramowicz', '00000000003',DATE '1980-11-11', 1), 
('Aleksandra', 'Kochanowicz', '00000000004',DATE '1992-10-05', 3), ('Anna', 'Bil', '00000000005',DATE '1972-10-05', 3), ('Lolek', 'UFo', '00000088007',DATE '1972-10-05', 1),
('Morze', 'Buehe', '00000033304',DATE '1962-02-24', 3), ('But', 'Mickiewicz', '00000070005',DATE '1987-10-05', 3), ('Bolek', 'Agent', '00000000007',DATE '1966-10-05', 5),
('Danka', 'Norek', '00000433304',DATE '1933-12-08', 2), ('Tadek', 'Norek', '00000099005',DATE '2001-10-05', 2), ('Krzychu', 'Krawczyk', '00500000007',DATE '1995-10-05', 5),
('Maciek', 'Zklanu', '00000000601',DATE '2000-01-02', 2), ('Ala', 'Kot', '00004000002',DATE '1990-01-02', 5), ('Piotr', 'Abramowicz', '00000320003',DATE '1980-11-11', 5), 
('Aleksandra', 'Kochanowicz', '00000036004',DATE '1992-10-05', 5), ('Anna', 'Bil', '00000990005',DATE '1944-10-05', 3), ('Lolek', 'UFo', '00000088517',DATE '1965-10-05', 1),
('Morze', 'Buehe', '00000733304',DATE '1961-02-24', 2), ('Adam', 'Mickiewicz', '00035070005',DATE '1989-10-05', 4), ('Bolek', 'Maciek', '00000170007',DATE '1956-10-05', 5),
('Danka', 'Norek', '00066433304',DATE '1939-12-08', 1), ('Tadek', 'Norek', '00005781005',DATE '2011-10-05', 2), ('Krzychu', 'Krawczyk', '00500054007',DATE '1999-10-05', 4);

INSERT INTO employees (first_name, last_name, pesel, birth_date) VALUES('Zbych', 'Kowalski', '00440000000',DATE '1958-02-01'), ('Jurek', 'Abs', '0047700000',DATE '1990-02-01'),
('Karolina', 'OFfilne', '00440000080',DATE '1985-02-01');

-- insert data into roles table - mozna ale nie trzeba podawac id jak jest auto increment

INSERT INTO roles (id, name) VALUES(1, 'PL'), (2, 'TCD'), (3, 'FCD'), (4, 'DEV');


-- insert data into projects table

INSERT INTO projects (name, realisation, manager_id) VALUES ('Project 1','INTERNAL', 3),  ('Project 2','EXTERNAL', 2),('Project 3','INTERNAL', 5),  ('Project 4','EXTERNAL', 7),
('Project 5','INTERNAL', 1),  ('Project 6','EXTERNAL', 6),('Project 7','INTERNAL', 11),  ('Project 8','EXTERNAL', 14), ('Project 9','INTERNAL', 13),  ('Project 10','EXTERNAL', 12);

-- insert data into employee_engagement table

INSERT INTO employee_engagement (employee_id, role_id, project_id, date_from, date_to, salary) VALUES 
(6, 1, 1, DATE '2000-01-01', DATE '2002-01-01', 1200.00), (6, 1, 3, DATE '2000-01-01', DATE '2001-01-01', 2000.00),(2, 2, 1, DATE '2000-01-01', DATE '2005-01-01', 3000.00),
(3, 4, 1, DATE '2001-01-01', DATE '2005-01-01', 1500.00), (1, 1, 1, DATE '2001-01-01', DATE '2011-01-01', 3500.00),(9, 1, 6, DATE '2001-01-01', DATE '2003-01-01', 2200.00),
(1, 1, 7, DATE '2002-01-01', DATE '2005-01-01', 5000.00), (2, 4, 4, DATE '2002-01-01', DATE '2007-01-01', 1500.00),(7, 2, 3, DATE '2002-01-01', DATE '2012-01-01', 1000.00),
(1, 2, 2, DATE '2003-01-01', DATE '2004-01-01', 1800.00), (8, 2, 5, DATE '2003-01-01', DATE '2013-01-01', 2000.00),(16, 3, 2, DATE '2003-01-01', DATE '2010-01-01', 1400.00),
(6, 1, 1, DATE '2004-01-01', DATE '2006-01-01', 2500.00), (12, 1, 2, DATE '2004-01-01', DATE '2008-01-01', 1400.00),(8, 1, 5, DATE '2004-01-01', DATE '2011-01-01', 3000.00),
(1, 3, 6, DATE '2005-01-01', DATE '2010-01-01', 1200.00), (11, 3, 8, DATE '2005-01-01', DATE '2006-01-01', 3000.00),(3, 2, 4, DATE '2005-01-01', DATE '2013-01-01', 1200.00),
(3, 2, 1, DATE '2006-01-01', DATE '2008-01-01', 2000.00), (7, 2, 7, DATE '2006-01-01', DATE '2011-01-01', 1000.00),(5, 4, 1, DATE '2006-01-01', DATE '2014-01-01', 2500.00),
(1, 1, 1, DATE '2007-01-01', DATE '2008-01-01', 3300.00), (20, 2, 5, DATE '2007-01-01', DATE '2009-01-01', 1200.00),(14, 1, 6, DATE '2007-01-01', DATE '2012-01-01', 1000.00),
(10, 1, 5, DATE '2008-01-01', DATE '2010-01-01', 1800.00), (9, 1, 1, DATE '2008-01-01', DATE '2013-01-01', 2000.00),(3, 2, 6, DATE '2008-01-01', DATE '2015-01-01', 1800.00),
(4, 3, 7, DATE '2009-01-01', DATE '2010-01-01', 1000.00), (6, 4, 8, DATE '2009-01-01', DATE '2011-01-01', 2500.00),(14, 3, 1, DATE '2009-01-01', DATE '2014-01-01', 1000.00),
(2, 1, 3, DATE '2010-01-01', DATE '2020-01-01', 1800.00), (6, 2, 2, DATE '2011-01-01', DATE '2018-01-01', 2500.00),(12, 4, 5, DATE '2012-01-01', DATE '2017-01-01', 1000.00),
(1, 1, 6, DATE '2012-01-01', DATE '2016-01-01', 1200.00);

INSERT INTO employee_engagement (employee_id, role_id, project_id, date_from,  salary) VALUES 
(1, 4, 2, DATE '2000-01-01', 1900.00), (6, 1, 1, DATE '2005-01-01', 2400.00),(2, 3, 5, DATE '2010-01-01',  2500.00),
(1, 2, 6, DATE '2000-01-01', 1700.00), (5, 4, 7, DATE '2005-01-01', 4500.00),(4, 2, 2, DATE '2010-01-01',  5700.00),
(4, 2, 1, DATE '2000-01-01', 1200.00), (3, 1, 3, DATE '2005-01-01', 2400.00),(2, 3, 5, DATE '2010-01-01',  2800.00),
(5, 4, 4, DATE '2000-01-01', 4200.00), (10, 2, 5, DATE '2005-01-01', 2700.00),(2, 3, 5, DATE '2010-01-01',  2500.00),
(7, 4, 5, DATE '2000-01-01', 1500.00), (6, 1, 8, DATE '2005-01-01', 2900.00),(7, 3, 2, DATE '2010-01-01',  3500.00),
(3, 2, 6, DATE '2000-01-01', 800.00), (4, 1, 1, DATE '2005-01-01', 3400.00),(8, 2, 8, DATE '2010-01-01',  2500.00),
(1, 4, 3, DATE '2000-01-01', 1800.00), (12, 2, 4, DATE '2005-01-01', 2800.00),(9, 3, 4, DATE '2010-01-01',  2500.00),
(11, 4, 8, DATE '2000-01-01', 1200.00), (4, 1, 1, DATE '2005-01-01', 2500.00),(5, 2, 7, DATE '2010-01-01',  2900.00),
(5, 4, 9, DATE '2000-01-01', 3200.00), (7, 2, 5, DATE '2005-01-01', 2400.00),(3, 3, 9, DATE '2010-01-01',  2000.00);