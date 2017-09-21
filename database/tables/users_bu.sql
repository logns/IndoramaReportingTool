#users_bu.sql 

drop table if exists users_bu;

CREATE TABLE IF NOT EXISTS users_bu 
(
	#Surrogate primary key
   	id integer auto_increment primary key,
        
    #foreign key bu.id	
	bu_id integer not null,
	         
	#foreign key users.id	
	users_id integer not null default -1,

	last_edit timestamp not null default current_timestamp on update current_timestamp,
	
	# adding unique constraint to users_id
    CONSTRAINT uc_users_bu UNIQUE (users_id)

) ENGINE =InnoDB default CHARSET=utf8;
alter table users_bu add index (users_id);
alter table users_bu add constraint constr_usersid UNIQUE (users_id);
alter table users_bu add foreign key (users_id) 
   references users (id) on delete cascade
   			 on update cascade;
alter table users_bu add index (bu_id);
alter table users_bu add foreign key (bu_id) 
   references bu (id) on delete cascade
   			 on update cascade;

ALTER TABLE `indorama_poly`.`users_bu` 
DROP INDEX `uc_users_bu` ,
ADD INDEX `uc_users_bu` (`users_id` ASC),
DROP INDEX `constr_usersid` ,
ADD INDEX `constr_usersid` (`users_id` ASC);
