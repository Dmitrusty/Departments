drop table if exists employees;
drop table if exists departments;


CREATE TABLE if not exists departments(
                                          id int primary key auto_increment,
                                          name varchar(255) not null
);

INSERT INTO departments values (1, 'IT');
INSERT INTO departments values (2, 'Sales');
INSERT INTO departments values (3, 'Security');
INSERT INTO departments values (4, 'Cleaners');

CREATE table if not exists employees(
                                        id int primary key auto_increment,
                                        name varchar (255) not null,
                                        startDate date not null,
                                        salary decimal(8, 2) not null,
                                        departmentId int not null references departments (id)
);

insert into employees (name, startDate, salary, departmentId)values ('Bob Marley', '1997-01-01', 2000.00, 1);
insert into employees (name, startDate, salary, departmentId)values ('Исаак Ньютон', '1685-03-03', 5000.00, 1);
insert into employees (name, startDate, salary, departmentId)values ('Альберт Эйнштейн', '1943-06-25', 4000.00, 1);
insert into employees (name, startDate, salary, departmentId)values ('Уи́лбур Райт', '1906-11-11', 1100, 2);
insert into employees (name, startDate, salary, departmentId)values ('О́рвил  Райт', '1906-11-11', 1100, 2);
insert into employees (name, startDate, salary, departmentId)values ('Томас Эдисон', '1927-12-25', 3000.15, 3);
insert into employees (name, startDate, salary, departmentId)values ('Адольф Гитлер', '1925-11-15', 100, 4);

