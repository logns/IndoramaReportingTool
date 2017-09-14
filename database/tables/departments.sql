# departments.sql 
#
# @author ms
#
#
drop table if exists departments;

CREATE TABLE IF NOT EXISTS departments
(
	#Surrogate primary key
   	id integer auto_increment primary key,
        
    #required group name 
	department_name varchar(32) not null default "",
	
	last_edit timestamp not null default current_timestamp on update current_timestamp,
	
	UNIQUE KEY `uk__department` (department_name)

) ENGINE =InnoDB default CHARSET=utf8;

create index departments_name_idx on departments(department_name);

insert into departments (department_name)
values
('A1');
insert into departments (department_name)
values
('A2');
insert into departments (department_name)
values
('A3');