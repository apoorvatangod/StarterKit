USE company;

-- a znajdz pracowników starszych niz X lat
SELECT * ,YEAR(CURDATE()) - YEAR(birth_date) AS age FROM employees
WHERE birth_date < DATE_SUB(CURDATE(), INTERVAL 30 YEAR); 
-- a.1 znajdz pracowników w których nazwisku wystepuje fraza 'ski'
SELECT * FROM employees
WHERE last_name LIKE '%ski'; 
-- a.2 znajdz pracowników których nazwisko nie zaczyna sie od 'ab' (wielkosc liter bez  znaczenia)
SELECT * FROM employees
WHERE last_name NOT LIKE 'ab%'; 
-- a.3 znajdz pracowników których nazwisko jest dluzsze niz N znaków
SELECT * FROM employees
WHERE CHAR_LENGTH(last_name) > 10; 
-- a.4 znajdz pracowników w których nazwisku na drugim miejscu wystepuje duza litera F
SELECT * FROM employees
WHERE last_name LIKE BINARY '_F%';
-- b znajdz pracowników w dziale X
SELECT e.first_name, e.last_name, e.pesel, d.name AS department FROM employees e
JOIN departments d ON e.department_id = d.id
WHERE d.name = 'IT Department';
-- c znajdz projekty w których zatrudniony byl pracownik z peselem X
SELECT e.pesel, p.name, ee.date_from, ee.date_to FROM 
(employee_engagement ee JOIN employees e ON ee.employee_id = e.id) JOIN projects p ON ee.project_id = p.id
WHERE e.pesel = 00000000000;

-- lub

SELECT * FROM employee_engagement ee
	JOIN employees e ON ee.employee_id = e.id
WHERE e.pesel = 00000000000;
-- d policz w ilu projektach aktualnie zatrudniony jest pracownik X
SELECT e.pesel, count(*) AS current_projects_count FROM employee_engagement ee
	JOIN employees e ON ee.employee_id = e.id
WHERE e.pesel = 00000000000 AND (ee.date_to IS NULL OR ee.date_to > CURDATE());

-- lub
SELECT count(*) FROM employee_engagement
WHERE employee_id = 1 AND (date_to IS NULL OR date_to > CURDATE());
-- e policz w ilu projektach w zeszlym roku zatrudniony byl pracownik X na stanowisku PL
SELECT e.pesel, count(*) AS last_year_projects_count FROM employee_engagement ee
	JOIN roles r ON ee.role_id = r.id JOIN employees e ON ee.employee_id = e.id
WHERE ee.employee_id = 1 AND r.name = 'PL' AND YEAR(ee.date_from) <= YEAR(DATE_SUB(CURDATE(), INTERVAL 1 YEAR))
AND (ee.date_to IS NULL OR YEAR(ee.date_to) >= YEAR(DATE_SUB(CURDATE(), INTERVAL 1 YEAR)));
-- f wyszukaj zewnetrzne projekty
SELECT * FROM projects
WHERE realisation = 'EXTERNAL';
-- g znajdz pracowników z dzialu X którzy pelnili w jakims projekcie przynajmniej 2 funkcje
SELECT e.pesel AS employee_pesel, count(DISTINCT ee.role_id) AS role_count, p.name AS project_name FROM employee_engagement ee
	JOIN employees e ON ee.employee_id = e.id JOIN projects p ON ee.project_id = p.id
WHERE e.department_id = 1
GROUP BY e.id, ee.project_id
HAVING count(DISTINCT ee.role_id) > 1;
-- h znajdz pracowników którzy pelnili w jakims projekcie funkcje TCD i DEV
SELECT ee.employee_id, ee.project_id, GROUP_CONCAT(DISTINCT r.name SEPARATOR ', ') AS employee_roles FROM employee_engagement ee
	JOIN employees e ON ee.employee_id = e.id JOIN roles r ON ee.role_id=r.id
WHERE 1=1
GROUP BY e.id, ee.project_id
HAVING employee_roles LIKE '%TCD%' AND employee_roles LIKE '%DEV%';

-- lub

SELECT ee.employee_id, ee.project_id, GROUP_CONCAT(DISTINCT ee.role_id SEPARATOR ', ') AS roles FROM employee_engagement ee
	JOIN employees e ON ee.employee_id = e.id
WHERE 1=1
GROUP BY e.id, ee.project_id
HAVING roles LIKE '%2%' AND roles LIKE '%4%';
-- i wyszukaj projekt, funkcje pracownika w którym pracownik mial w nim najwyzsza dniówke (ze  wszystkich projektów w systemie)
SELECT MAX(ee.salary) AS salary, p.name, ee.employee_id, r.name FROM (employee_engagement ee
	JOIN projects p ON ee.project_id = p.id) JOIN roles r ON ee.role_id=r.id;
-- i wyszukaj najlepiej zarabiajacego aktualnie pracownika (chodzi o sumaryczna wartosc dniówek danego pracownika ze wszystkich projektów, do których jest aktualnie przypisany)
SELECT SUM(ee.salary) AS salary,  ee.employee_id FROM employee_engagement ee
WHERE ee.date_to IS NULL OR ee.date_to > CURDATE()
GROUP BY ee.employee_id
ORDER BY salary DESC
LIMIT 1;
-- j znajdz pracownikow, ktorzy pracowali w projekcie X pomiedzy data Y i Z
SELECT * FROM employee_engagement ee
	JOIN projects p ON ee.project_id = p.id
WHERE p.name = 'Project 1'
AND (DATE('2010-01-01') > date_from AND (DATE('2005-01-01') <= date_to OR date_to IS NULL));
-- k znajdz pracownikow nie przypisanych do zadnego z dzialów
SELECT * FROM employees e
WHERE e.department_id IS NULL;
-- l znajdz pracownikow zarabaiajacych aktualnie w jednym z projektow (dziennie) wiecej niz X
SELECT e.pesel, ee.salary AS salary, ee.project_id AS project FROM employee_engagement ee
	JOIN employees e ON ee.employee_id = e.id
WHERE ee.date_to IS NULL OR ee.date_to > CURDATE()
GROUP BY e.id, ee.project_id
HAVING salary > 2000;
-- m znajdz pracownikow zarabaiajacych aktualnie (dziennie) wiecej niz X
SELECT e.pesel, SUM(ee.salary) AS salary_sum FROM employee_engagement ee
	JOIN employees e ON ee.employee_id = e.id
WHERE ee.date_to IS NULL OR ee.date_to > CURDATE()
GROUP BY e.id
HAVING salary_sum > 2000;