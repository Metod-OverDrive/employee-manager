-- liquibase formatted sql
-- changeset eve:1

-- Table: employees
create table if not exists employees
(
    id       bigserial,
    name     varchar(255) not null,
    username varchar(255) not null unique,
    phone_number varchar(255) not null,
    password varchar(255) not null,
    is_active boolean not null,
    primary key (id)
);

--Table: tasks
create table if not exists tasks
(
    id              bigserial,
    title           varchar(255) not null,
    description     varchar(255) null,
    status          varchar(255) not null,
    expiration_data date    null,
    primary key (id)
);

-- Table: departments
create table if not exists teams
(
    id bigserial,
    name varchar(255) not null,
    primary key (id)
);

-- Table: roles
create table if not exists roles
(
    id   bigserial,
    name varchar(255) not null,
    primary key (id)
);

-- Table for mapping departments and tasks: departments_tasks
create table if not exists teams_tasks
(
    team_id bigserial,
    task_id bigserial,
    primary key (team_id, task_id)
);

-- Table for mapping employees and roles: employees_roles
create table if not exists employees_roles
(
    employee_id bigserial,
    role_id bigserial,
    primary key (employee_id, role_id)
);

-- Table for mapping departments and employees: departments_employees
create table if not exists teams_employees
(
    team_id bigserial,
    employee_id bigserial,
    primary key (team_id, employee_id)
);

alter table teams_tasks
    add constraint fk_departments_tasks_departments foreign key (team_id) references teams (id) on delete cascade on update no action;
alter table teams_tasks
    add constraint fk_departments_tasks_tasks foreign key (task_id) references tasks (id) on delete cascade on update no action;

alter table employees_roles
    add constraint fk_employees_roles_employees foreign key (employee_id) references employees (id) on delete cascade on update no action;
alter table employees_roles
    add constraint fk_employees_roles_roles foreign key (role_id) references roles (id) on delete cascade on update no action;

alter table teams_employees
    add constraint fk_departments_employees_employees foreign key (employee_id) references employees (id) on delete cascade on update no action;
alter table teams_employees
    add constraint fk_departments_employees_departments foreign key (team_id) references teams (id) on delete cascade on update no action;


