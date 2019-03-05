INSERT INTO users (username, password, enabled) VALUES ('user', '40408537d958bee8c46851120c94e4ac6ba054458ba1d791aeaf8e365ec2d2374817ba13edd03fdf', true);
INSERT INTO users (username, password, enabled) VALUES ('userN', '40408537d958bee8c46851120c94e4ac6ba054458ba1d791aeaf8e365ec2d2374817ba13edd03fdf', false);
INSERT INTO users (username, password, enabled, email) VALUES ('admin', '40408537d958bee8c46851120c94e4ac6ba054458ba1d791aeaf8e365ec2d2374817ba13edd03fdf', true, 'blachut.pawel@gmail.com');

INSERT INTO roles (username, role) VALUES ('user', 'ROLE_USER');
INSERT INTO roles (username, role) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO roles (username, role) VALUES ('userN', 'ROLE_USER');