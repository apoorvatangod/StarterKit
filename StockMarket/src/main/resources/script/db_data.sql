-- use stockmarket database

USE stockmarket;

-- insert data into player_share table

INSERT INTO player_share (name, quantity) VALUES ('PKO', 10), ('KGHM', 7), ('PGNIG', 0);

-- insert data into offer table

INSERT INTO offer (action_type, share, quantity, price, date, currency, comission, status) VALUES 
('BUY', 'PKO', 5, 1000, DATE '2001-05-22', 'PLN', 500, 'ACTIVE'),
('SELL', 'KGHM', 10, 2200, DATE '2001-02-14', 'PLN', 1000, 'ACTIVE'),
('BUY', 'PGNIG', 20, 6000, DATE '2010-01-05', 'PLN', 700, 'COMPLETED'),
('SELL', 'KGHM', 7, 3340, DATE '2011-10-01', 'PLN', 500, 'COMPLETED'),
('BUY', 'PKO', 100, 1360, DATE '2015-03-16', 'PLN', 800, 'COMPLETED');

-- insert data into share_data table

INSERT INTO share_data (name, price, date) VALUES 
('PKO', 1000, DATE '2001-05-22'), ('KGHM', 1000, DATE '2001-05-22'), ('PGNIG', 1000, DATE '2001-05-22'),
('PKO', 1000, DATE '2001-05-23'), ('KGHM', 1000, DATE '2001-05-25'), ('PGNIG', 1000, DATE '2001-05-27'),
('PKO', 1000, DATE '2001-05-24'), ('KGHM', 1000, DATE '2001-05-26'), ('PGNIG', 1000, DATE '2001-05-26'),
('PKO', 1200, DATE '2010-01-01'), ('PKO', 1100, DATE '2010-01-02'), ('PKO', 1000, DATE '2010-01-03'),
('PKO', 900, DATE '2010-01-04'), ('PKO', 1000, DATE '2010-01-05'), ('PKO', 1100, DATE '2010-01-06'),
('PKO', 1300, DATE '2010-01-07'), ('PKO', 1400, DATE '2010-01-08'), ('PKO', 1400, DATE '2010-01-09'),
('PKO', 1500, DATE '2010-01-10'), ('PKO', 1600, DATE '2010-01-11'), ('PKO', 1100, DATE '2010-01-12'),
('PKO', 1500, DATE '2010-01-13'), ('PKO', 1600, DATE '2010-01-14'), ('PKO', 1100, DATE '2010-01-15'),
('PKO', 1500, DATE '2010-01-16'), ('PKO', 1600, DATE '2010-01-17'), ('PKO', 1100, DATE '2010-01-18');

-- insert data into player_found table

INSERT INTO player_found (currency, quantity) VALUES ('PLN', 1000000), ('EUR', 1), ('USD', 100);

-- insert data into found_transaction table

INSERT INTO found_transaction (type, currency, quantity, date) VALUES 
('DEPOSIT', 'PLN', 1000,  DATE '2001-05-22'),('WITHDRAW', 'PLN', 1000,  DATE '2001-05-21'),
('DEPOSIT', 'PLN', 1000,  DATE '2001-05-25'),('WITHDRAW', 'PLN', 1000,  DATE '2001-05-22'),
('DEPOSIT', 'EUR', 1000,  DATE '2001-05-25'),('WITHDRAW', 'EUR', 1000,  DATE '2001-05-22'),
('DEPOSIT', 'EUR', 1000,  DATE '2001-05-21'),('WITHDRAW', 'EUR', 1000,  DATE '2001-05-26');