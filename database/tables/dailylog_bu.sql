#users_bu.sql 

drop table if exists dailylog_bu;

CREATE TABLE IF NOT EXISTS dailylog_bu 
(
	#Surrogate primary key
   	id integer auto_increment primary key,
        
    #foreign key bu.id	
	bu_id integer not null,
	         
	#foreign key users.id	
	dailylog_id integer not null default -1,

	last_edit timestamp not null default current_timestamp on update current_timestamp,
	
	# adding unique constraint to users_id
    CONSTRAINT uc_dailylog_bu UNIQUE (dailylog_id)

) ENGINE =InnoDB default CHARSET=utf8;
alter table dailylog_bu add index (dailylog_id);
alter table dailylog_bu add constraint constr_dailylogid UNIQUE (dailylog_id);
alter table dailylog_bu add foreign key (dailylog_id) 
   references dailylog (id) on delete cascade
   			 on update cascade;
alter table dailylog_bu add index (bu_id);
alter table dailylog_bu add foreign key (bu_id) 
   references bu (id) on delete cascade
   			 on update cascade;
ALTER TABLE `indorama_poly`.`dailylog_bu` 
DROP INDEX `uc_dailylog_bu` ,
ADD INDEX `uc_dailylog_bu` (`dailylog_id` ASC),
DROP INDEX `constr_dailylogid` ,
ADD INDEX `constr_dailylogid` (`dailylog_id` ASC);


