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
	user_id INT,
	PRIMARY KEY (role_id),
	UNIQUE(role)
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