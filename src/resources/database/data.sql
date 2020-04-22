INSERT INTO roles (role) VALUES ('ROLE_USER');
INSERT INTO roles (role) VALUES ('ROLE_ADMIN');
INSERT INTO roles (role) VALUES ('ROLE_GUEST');

INSERT INTO users (username, password, first_name, role_id) VALUES ('user', '40408537d958bee8c46851120c94e4ac6ba054458ba1d791aeaf8e365ec2d2374817ba13edd03fdf', 'userName1', (SELECT role_id FROM roles WHERE role='ROLE_USER'));
INSERT INTO users (username, password, first_name, enabled, role_id) VALUES ('guest', '40408537d958bee8c46851120c94e4ac6ba054458ba1d791aeaf8e365ec2d2374817ba13edd03fdf', 'userName2', false, (SELECT role_id FROM roles WHERE role='ROLE_GUEST'));
INSERT INTO users (username, password, first_name, last_name, enabled, email, role_id) VALUES ('admin', '40408537d958bee8c46851120c94e4ac6ba054458ba1d791aeaf8e365ec2d2374817ba13edd03fdf', 'adminFirstName', 'adminLastName',true, 'adres.email@email.com', (SELECT role_id FROM roles WHERE role='ROLE_ADMIN'));

INSERT INTO users (username, password, first_name, last_name, enabled, email, role_id) VALUES ('deleteUser', 'pass', 'deleteFirstName', 'deleteLastName',true, 'adres.email@email.com', (SELECT role_id FROM roles WHERE role='ROLE_ADMIN'));

INSERT INTO users (username, password, first_name, last_name, enabled, email, role_id) VALUES ('updateUser', 'pass', 'updateFirstName', 'updateLastName' ,true, 'update.email@email.com', (SELECT role_id FROM roles WHERE role='ROLE_ADMIN'));