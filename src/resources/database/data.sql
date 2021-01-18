INSERT INTO roles (role) VALUES ('ROLE_USER');
INSERT INTO roles (role) VALUES ('ROLE_ADMIN');
INSERT INTO roles (role) VALUES ('ROLE_GUEST');

INSERT INTO users (username, password, first_name, role_id) VALUES ('user', '$2a$10$vz/0q8l5p7f6fNFUgFn/fueDX65INhr47s/LqLMoYPlKrtUbmAqxK', 'userName1', (SELECT role_id FROM roles WHERE role='ROLE_USER'));
INSERT INTO users (username, password, first_name, enabled, role_id) VALUES ('guest', '$2a$10$vz/0q8l5p7f6fNFUgFn/fueDX65INhr47s/LqLMoYPlKrtUbmAqxK', 'userName2', false, (SELECT role_id FROM roles WHERE role='ROLE_GUEST'));
INSERT INTO users (username, password, first_name, last_name, enabled, email, role_id) VALUES ('admin', '$2a$10$vz/0q8l5p7f6fNFUgFn/fueDX65INhr47s/LqLMoYPlKrtUbmAqxK', 'adminFirstName', 'adminLastName',true, 'adres.email@email.com', (SELECT role_id FROM roles WHERE role='ROLE_ADMIN'));

INSERT INTO users (username, password, first_name, last_name, enabled, email, role_id) VALUES ('deleteUser', 'pass', 'deleteFirstName', 'deleteLastName',true, 'adres.email@email.com', (SELECT role_id FROM roles WHERE role='ROLE_USER'));

INSERT INTO users (username, password, first_name, last_name, enabled, email, role_id) VALUES ('updateUser', 'pass', 'updateFirstName', 'updateLastName' ,true, 'update.email@email.com', (SELECT role_id FROM roles WHERE role='ROLE_USER'));
INSERT INTO users (username, password, first_name, role_id) VALUES ('UserPassChange', '$2a$10$vz/0q8l5p7f6fNFUgFn/fueDX65INhr47s/LqLMoYPlKrtUbmAqxK', 'userPassChange', (SELECT role_id FROM roles WHERE role='ROLE_USER'));

INSERT INTO entity_types (entitytype) VALUES ('New user registered');
INSERT INTO entity_types (entitytype) VALUES ('User deleted');
INSERT INTO entity_types (entitytype) VALUES ('Sensor error');

INSERT INTO followers (user_id, entity_type_id) VALUES ((SELECT user_id FROM users WHERE user_id = 1), (SELECT id FROM entity_types WHERE entitytype = 'Sensor error'));
INSERT INTO followers (user_id, entity_type_id) VALUES ((SELECT user_id FROM users WHERE user_id = 2), (SELECT id FROM entity_types WHERE entitytype = 'Sensor error'));
INSERT INTO followers (user_id, entity_type_id) VALUES ((SELECT user_id FROM users WHERE user_id = 2), (SELECT id FROM entity_types WHERE entitytype = 'User deleted'));
INSERT INTO followers (user_id, entity_type_id) VALUES ((SELECT user_id FROM users WHERE user_id = 3), (SELECT id FROM entity_types WHERE entitytype = 'Sensor error'));

INSERT INTO notification_entity (entity_type_id, sender_id, create, message) VALUES (1, 5, DATE '2021-01-12', 'CREATED NOTIFICATION ENTITY');
INSERT INTO notification_entity (entity_type_id, sender_id, create, message) VALUES (2, 6, DATE '2021-01-11', 'NOTIFICATION ENTITY TO DELETE');
INSERT INTO notification_entity (entity_type_id, sender_id, create, message) VALUES (3, 7, DATE '2021-01-10', 'NOTIFICATION TO ADD TEST');
INSERT INTO notification_entity (entity_type_id, sender_id, create, message) VALUES (3, 8, DATE '2021-01-09', 'NOTIFICATION MESSAGE TEST');
INSERT INTO notification_entity (entity_type_id, sender_id, create, message) VALUES (2, 8, DATE '2021-01-08', 'Notification to test remove in service');

INSERT INTO notification (user_id, notification_id) VALUES (2, 3);
INSERT INTO notification (user_id, notification_id) VALUES (2, 4);

INSERT INTO notification (user_id, notification_id) VALUES (5, 5);
INSERT INTO notification (user_id, notification_id) VALUES (6, 5);