drop table if exists employees;
drop table if exists departments;


CREATE TABLE if not exists departments(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(255) not null
);

INSERT INTO departments values (1, 'IT');
INSERT INTO departments values (2, 'Sales');
INSERT INTO departments values (3, 'Security');
INSERT INTO departments values (4, 'Cleaners');

CREATE TABLE IF NOT EXISTS employees(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR (255) NOT NULL,
    startDate DATE NOT NULL,
    salary DECIMAL(8, 2) NOT NULL,
    department_id INT NOT NULL,
    FOREIGN KEY (department_id) REFERENCES departments (id)

);

insert into employees (name, startDate, salary, department_id)values ('Bob Marley', '1997-01-01', 2000.00, 1);
insert into employees (name, startDate, salary, department_id)values ('Исаак Ньютон', '1685-03-03', 5000.00, 1);
insert into employees (name, startDate, salary, department_id)values ('Альберт Эйнштейн', '1943-06-25', 4000.00, 1);
insert into employees (name, startDate, salary, department_id)values ('Уи́лбур Райт', '1906-11-11', 1100, 2);
insert into employees (name, startDate, salary, department_id)values ('О́рвил  Райт', '1906-11-11', 1100, 2);
insert into employees (name, startDate, salary, department_id)values ('Томас Эдисон', '1927-12-25', 3000.15, 3);
insert into employees (name, startDate, salary, department_id)values ('Адольф Гитлер', '1925-11-15', 100, 4);

