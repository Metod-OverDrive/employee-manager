INSERT INTO teams (name)
VALUES ('Department A'),
       ('Department B'),
       ('Department C');

INSERT INTO employees (name, username, phone_number, password, is_active)
VALUES ('Odin Emp', 'odin@gmail.com', '1234567890', '$2a$10$Xl0yhvzLIaJCDdKBS0Lld.ksK7c2Zytg/ZKFdtIYYQUv8rUfvCR4W', true),
       ('Vtoroi Emp', 'vtoroi@gmail.com', '9876543210', '$2a$10$Xl0yhvzLIaJCDdKBS0Lld.ksK7c2Zytg/ZKFdtIYYQUv8rUfvCR4W', true),
       ('Treti Emp', 'treti@gmail.com', '5555555555', '$2a$10$Xl0yhvzLIaJCDdKBS0Lld.ksK7c2Zytg/ZKFdtIYYQUv8rUfvCR4W', true);

INSERT INTO roles (name)
VALUES ('MANAGER'),
       ('EMPLOYEE'),
       ('ADMIN');

INSERT INTO teams_employees (team_id, employee_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);

INSERT INTO employees_roles (employee_id, role_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);

INSERT INTO tasks (title, description, status, expiration_data)
VALUES ('Task 1', 'Description for Task 1', 'DONE', '2024-01-01'),
       ('Task 2', 'Description for Task 2', 'TODO', '2024-02-01'),
       ('Task 3', 'Description for Task 3', 'IN_PROGRESS', '2024-03-01'),
       ('Task 4', 'Description for Task 3', 'DONE_EXPIRED', '2024-04-01');

INSERT INTO teams_tasks (team_id, task_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);