-- company database creation

CREATE DATABASE IF NOT EXISTS company;

USE company;

-- Table structure for `departments`

DROP TABLE IF EXISTS departments;

CREATE TABLE departments (
	id bigint(20) unsigned NOT NULL AUTO_INCREMENT,
	name varchar(50) NOT NULL,
	PRIMARY KEY (id)
)DEFAULT CHARSET=utf8;

-- Table structure for `employees`

DROP TABLE IF EXISTS employees;

CREATE TABLE employees (
	id bigint(20) unsigned NOT NULL AUTO_INCREMENT,
	first_name varchar(20) NOT NULL,
	last_name varchar(20) NOT NULL,
	pesel varchar(11) UNIQUE NOT NULL,
	birth_date date NOT NULL,
	department_id bigint(20) unsigned NULL,
	PRIMARY KEY(id),
	CONSTRAINT fk_employees_department
		FOREIGN KEY (department_id)
		REFERENCES departments(id)
)DEFAULT CHARSET=utf8;

-- Table structure for `roles`

DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
	id bigint(20) unsigned NOT NULL AUTO_INCREMENT,
	name varchar(50) NOT NULL UNIQUE,
	PRIMARY KEY(id)
)DEFAULT CHARSET=utf8;


-- Table structure for `projects`

DROP TABLE IF EXISTS projects;

CREATE TABLE projects (
	id bigint(20) unsigned NOT NULL AUTO_INCREMENT,
	name varchar(100) NOT NULL UNIQUE,
	realisation enum('INTERNAL', 'EXTERNAL') DEFAULT 'INTERNAL',
	manager_id bigint(20) unsigned DEFAULT NULL,
	PRIMARY KEY(id),
	CONSTRAINT fk_projects_employees
		FOREIGN KEY (manager_id)
		REFERENCES employees(id)
)DEFAULT CHARSET=utf8;

-- Table structure for `employee_engagement`

DROP TABLE IF EXISTS employee_engagement;

CREATE TABLE employee_engagement(
	id bigint(20) unsigned NOT NULL AUTO_INCREMENT,
	employee_id bigint(20) unsigned NOT NULL,
	role_id bigint(20) unsigned NOT NULL,
	project_id bigint(20) unsigned NOT NULL,
	date_from date NOT NULL,
	date_to date DEFAULT NULL,
	salary decimal(15,2) NOT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_employees_engagment_employees
		FOREIGN KEY (employee_id)
		REFERENCES employees(id),
	CONSTRAINT fk_employees_engagment_roles
		FOREIGN KEY (role_id)
		REFERENCES roles(id),
	CONSTRAINT fk_employees_engagment_projects
		FOREIGN KEY (project_id)
		REFERENCES projects(id)
)DEFAULT CHARSET=utf8;