CREATE TABLE users (
	id INT NOT NULL AUTO_INCREMENT,
	nickname VARCHAR(128) NOT NULL,
	password VARCHAR(128) NOT NULL,
	first_name VARCHAR(128) NOT NULL,
	last_name VARCHAR(128),
	email VARCHAR(128),
	enabled BOOL NOT NULL DEFAULT FALSE,
	PRIMARY KEY (id),
	UNIQUE(nickname),
);

CREATE TABLE roles (
	id INT NOT NULL AUTO_INCREMENT,
	role VARCHAR(128) NOT NULL DEFAULT 'ROLE_USER',
	user_id INT,
	FOREIGN KEY (user_id) REFERENCES users(id),
	PRIMARY KEY (id)
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