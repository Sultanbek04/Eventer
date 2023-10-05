create database it_company;
use it_company;
create table employees(
                          id int auto_increment primary key,
                          first_name varchar(20),
                          last_name varchar(20),
                          department varchar(20),
                          salary int
);
insert into employees(first_name, last_name,department,salary)VALUES('Ivan','Ivanov','IT',1000);
insert into employees(first_name, last_name,department,salary)VALUES('Petr','Petrov','IT',2000);