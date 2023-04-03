INSERT INTO users VALUES ('keith', '{noop}keithpw', 'email', 'i am keith');
INSERT INTO user_roles(username, role) VALUES ('keith', 'ROLE_USER');

INSERT INTO users VALUES ('john', '{noop}johnpw', 'email', 'i am john');
INSERT INTO user_roles(username, role) VALUES ('john', 'ROLE_ADMIN');
INSERT INTO user_roles(username, role) VALUES ('john', 'ROLE_USER');

INSERT INTO users VALUES ('mary', '{noop}marypw', 'email', 'i am mary');
INSERT INTO user_roles(username, role) VALUES ('mary', 'ROLE_USER');