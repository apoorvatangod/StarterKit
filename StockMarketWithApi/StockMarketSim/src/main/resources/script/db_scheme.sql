-- stockmarket database creation

CREATE DATABASE IF NOT EXISTS stockmarket;

USE stockmarket;

-- Table structure for `player_share`

DROP TABLE IF EXISTS player_share;

CREATE TABLE player_share (
	`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
	`name` varchar(50) NOT NULL,
	`quantity` int,
	PRIMARY KEY (id)
)DEFAULT CHARSET=utf8;

-- Table structure for `offer`

DROP TABLE IF EXISTS offer;

CREATE TABLE offer (
	`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
	`action_type` enum('BUY', 'SELL') NOT NULL,
	`share` varchar(50) NOT NULL,
	`quantity` int NOT NULL,
	`price` int NOT NULL,
	`date` date NOT NULL,
	`currency` varchar(50) NOT NULL,
	`comission` int NOT NULL,
	`status` enum('ACTIVE', 'COMPLETED', 'OUTDATED') DEFAULT 'ACTIVE',
	PRIMARY KEY (id)
)DEFAULT CHARSET=utf8;

-- Table structure for `share_data`

DROP TABLE IF EXISTS share_data;

CREATE TABLE share_data (
	`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
	`name` varchar(50) NOT NULL,
	`price` int NOT NULL,
	`date` date NOT NULL,
	PRIMARY KEY (id)
)DEFAULT CHARSET=utf8;

-- Table structure for `player_found`

DROP TABLE IF EXISTS player_found;

CREATE TABLE player_found (
	`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
	`currency` varchar(50) NOT NULL,
	`quantity` int,
	PRIMARY KEY (id)
)DEFAULT CHARSET=utf8;

-- Table structure for `found_transaction`

DROP TABLE IF EXISTS found_transaction;

CREATE TABLE found_transaction (
	`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
	`type` enum('DEPOSIT', 'WITHDRAW') NOT NULL,
	`currency` varchar(50) NOT NULL,
	`quantity` int,
	`date` date NOT NULL,
	PRIMARY KEY (id)
)DEFAULT CHARSET=utf8;
