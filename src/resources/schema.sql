CREATE TABLE users (
	id INT NOT NULL AUTO_INCREMENT,
	username VARCHAR(128) NOT NULL,
	password VARCHAR(128) NOT NULL,
	enabled BOOL NOT NULL DEFAULT False,
	email VARCHAR(128),
	PRIMARY KEY (id),
	UNIQUE (username)
);

CREATE TABLE roles (
	id INT NOT NULL AUTO_INCREMENT,
	username VARCHAR(128) NOT NULL,
	role VARCHAR(128) NOT NULL DEFAULT 'ROLE_USER',
	userID INT, PRIMARY KEY (id),
 	FOREIGN KEY (userID) REFERENCES users(id),
	UNIQUE (username)
);

CREATE TABLE logs (
    date TIMESTAMP,
    logger VARCHAR(100),
    level VARCHAR(100),
    message TEXT,
    exception TEXT
);