INSERT INTO roles (name)
VALUES
('ROLE_USER'), ('ROLE_ADMIN');
INSERT INTO users (username, password, email)
VALUES
('user', '$2a$12$6gCcdObtGWkQGMk8sspEy.hhEEj8biY1Fjs7dLkOdv7ph6RC7RsnS', 'user@gmail.com'),
('admin', '$2a$12$NMmaNM.VXuMEjQ1WfVe7WeUADZdVWkJW.lm4g0YCwH5yVIEUTwVeS', 'admin@gmail.com');
INSERT INTO users_roles (user_id, role_id)
VALUES
(1, 1),
(1, 2),
(2, 2);

INSERT INTO tasks (name, user_id)
VALUES
('test task', 1), ('english task', 1), ('spring task', 1), ('java task', 2), ('kotlin task', 2);