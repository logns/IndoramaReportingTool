#users_departments.sql 

drop table if exists users_departments;

CREATE TABLE IF NOT EXISTS users_departments 
(
	#Surrogate primary key
   	id integer auto_increment primary key,
        
    #foreign key departments.id	
	departments_id integer not null,
	         
	#foreign key users.id	
	users_id integer not null default -1,

	last_edit timestamp not null default current_timestamp on update current_timestamp,
	
	# adding unique constraint to users_id
    CONSTRAINT uc_users_departments UNIQUE (users_id)

) ENGINE =InnoDB default CHARSET=utf8;
alter table users_departments add index (users_id);
alter table users_departments add constraint constr_usersid UNIQUE (users_id);
alter table users_departments add foreign key (users_id) 
   references users (id) on delete cascade
   			 on update cascade;
alter table users_departments add index (departments_id);
alter table users_departments add foreign key (departments_id) 
   references departments (id) on delete cascade
   			 on update cascade;
ALTER TABLE `indorama_poly`.`users_departments` 
DROP INDEX `uc_users_departments` ,
ADD INDEX `uc_users_departments` (`users_id` ASC),
DROP INDEX `constr_usersid` ,
ADD INDEX `constr_usersid` (`users_id` ASC);

