CREATE USER IF NOT EXISTS 'user_ro'@'localhost' IDENTIFIED BY 'ro_pass';
CREATE USER IF NOT EXISTS 'user_rw_projekt'@'localhost' IDENTIFIED BY 'rw_pass';

GRANT SELECT ON company.* TO 'user_ro'@'localhost';
GRANT ALL ON company.departments TO 'user_rw_projekt'@'localhost';

REVOKE SELECT ON company.* FROM 'user_ro'@'localhost';
REVOKE ALL ON company.departments FROM 'user_rw_projekt'@'localhost';

DROP USER 'user_ro'@'localhost';
DROP USER 'user_rw_projekt'@'localhost';

SELECT * FROM mysql.user;