USE company;

CREATE OR REPLACE VIEW widok1 AS 
	SELECT e.first_name AS IMIE, e.last_name AS NAZWISKO, GROUP_CONCAT(DISTINCT p.name SEPARATOR ', ') AS PROJECT FROM employee_engagement ee
		JOIN employees e ON ee.employee_id = e.id JOIN projects p ON ee.project_id = p.id
	WHERE ee.date_to IS NULL OR ee.date_to > CURDATE()
	GROUP BY e.id, p.id;
