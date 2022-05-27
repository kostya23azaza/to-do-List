create table users (
  id                    bigserial,
  username              varchar(30) not null unique,
  password              varchar(80) not null,
  email                 varchar(50) unique,
  primary key (id)
);

create table roles (
  id                    bigserial,
  name                  varchar(50),
  primary key (id)
);

CREATE TABLE users_roles (
  user_id               bigint not null,
  role_id               int not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);

CREATE TABLE tasks (
  id                    bigserial,
  name                  varchar(255) not null,
  user_id bigint references users (id),
  primary key (id)
);

insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN');

insert into users (username, password, email)
values
('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com'),
('admin', '$2a$10$fymdKTYFSdCH5FJTBdqWp.5IKTnAwQzDLBDUcXPXd3bsL1VDZKWhW', 'admin@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1),
(1, 2),
(2, 2);

insert into tasks (name, user_id)
values
('test task', 1), ('english task', 1), ('spring task', 1), ('java task', 2), ('kotlin task', 2));