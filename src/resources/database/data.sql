INSERT INTO users (username, password, first_name) VALUES ('user', '40408537d958bee8c46851120c94e4ac6ba054458ba1d791aeaf8e365ec2d2374817ba13edd03fdf', 'userName1');
INSERT INTO users (username, password, first_name, enabled) VALUES ('guest', '40408537d958bee8c46851120c94e4ac6ba054458ba1d791aeaf8e365ec2d2374817ba13edd03fdf', 'userName2', false);
INSERT INTO users (username, password, first_name, last_name, enabled, email) VALUES ('admin', '40408537d958bee8c46851120c94e4ac6ba054458ba1d791aeaf8e365ec2d2374817ba13edd03fdf', 'adminFirstName', 'adminLastName',true, 'adres.email@email.com');

INSERT INTO roles (role, user_id) VALUES ('ROLE_USER', (SELECT id FROM users WHERE username='user'));
INSERT INTO roles (role, user_id) VALUES ('ROLE_ADMIN', (SELECT id FROM users WHERE username='admin'));
INSERT INTO roles (role, user_id) VALUES ('ROLE_GUEST', (SELECT id FROM users WHERE username='guest'));