CREATE TABLE users (
	user_id INT NOT NULL AUTO_INCREMENT,
	username VARCHAR(128) NOT NULL,
	password VARCHAR(128) NOT NULL,
	first_name VARCHAR(128) NOT NULL,
	last_name VARCHAR(128),
	email VARCHAR(128),
	enabled BOOL NOT NULL DEFAULT FALSE,
	role_id INT,
	PRIMARY KEY (user_id),
	UNIQUE(username)
);

CREATE TABLE roles (
	role_id INT NOT NULL AUTO_INCREMENT,
	role VARCHAR(128) NOT NULL DEFAULT 'ROLE_USER',
	PRIMARY KEY (role_id),
	UNIQUE(role)
);

CREATE TABLE followers (
	id INT NOT NULL AUTO_INCREMENT,
	user_id INT,
	entity_type_id INT,
	PRIMARY KEY (id),
);

CREATE TABLE entity_types (
	id INT NOT NULL AUTO_INCREMENT,
	entity_type VARCHAR(128) NOT NULL,
	PRIMARY KEY (id),
);

CREATE TABLE notification_entity (
	id INT NOT NULL AUTO_INCREMENT,
	entity_type_id INT,
	sender INT NOT NULL,
	create DATETIME NOT NULL,
	message  VARCHAR(256) NOT NULL,
	PRIMARY KEY (id),
);

CREATE TABLE notification (
	id INT NOT NULL AUTO_INCREMENT,
	user_id INT NOT NULL,
	notification_id INT NOT NULL,
	enabled BOOL NOT NULL DEFAULT TRUE,
	PRIMARY KEY (id),
);

CREATE TABLE logs (
	id INT NOT NULL AUTO_INCREMENT,
    date TIMESTAMP,
    logger VARCHAR(100),
    level VARCHAR(100),
    message TEXT,
    exception TEXT,
    PRIMARY KEY (id)
);

