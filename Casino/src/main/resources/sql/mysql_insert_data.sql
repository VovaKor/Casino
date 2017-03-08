INSERT INTO feedback(feedback_id, message, email, message_time) VALUES(1, 'Hello everyone', 'vasya228@gmail.com', '2017-01-19 03:14:07');
INSERT INTO feedback(feedback_id, message, email, message_time) VALUES(2, 'Hello everyone', 'vasya1488@gmail.com', '2017-01-19 03:14:07');
INSERT INTO feedback(feedback_id, message, email, message_time) VALUES(3, 'Hello everyone', 'vasya666@gmail.com', '2017-01-19 03:14:07');
INSERT INTO feedback(feedback_id, message, email, message_time) VALUES(4, 'Hello everyone', 'vasya322@gmail.com', '2017-01-19 03:14:07');

INSERT INTO automat(automat_id, automat_name, description) 
            VALUES (1, 'Slot machine','A slot machine is a casino gambling machine with three or more reels which spin when a button is pushed.');

INSERT INTO roles(roles_id, role_name, description) 
            VALUES (1, 'root', 'Administrative user, has access to everything.');
INSERT INTO roles(roles_id, role_name, description) 
            VALUES (2, 'moderator', 'Can edit feedbacks, comments and content.');
INSERT INTO roles(roles_id, role_name, description) 
            VALUES (3, 'player', 'Can sign in, operate his balance, play games, edit his own personal data, view games history and site''s content, leave comments and feedback.');
INSERT INTO roles(roles_id, role_name, description) 
            VALUES (4, 'guest', 'Can sign up, operate his temporary balance, view site''s content, play games and leave feedback');


INSERT INTO user(login_id, password, roles_id, balance, email) VALUES('erik12', 'sdf2342dgsd', 1, 500.00, 'eri12@gmail.com');
INSERT INTO user_data(passport, login_id, name, surname, patronymic, gender, birth_day, country, city, address, telephone) VALUES (
  'NM302402', 'erik12', 'Erik', 'Piterson', 'Edson', 1, '1982-01-19', 'Canada', 'Ottava', 'unknown', '123412412'
);

