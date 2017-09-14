#users_bu.sql 

drop table if exists dailylog_users;

CREATE TABLE IF NOT EXISTS dailylog_users 
(
	#Surrogate primary key
   	id integer auto_increment primary key,
        
    #foreign key users.id	
	users_id integer not null,
	         
	#foreign key users.id	
	dailylog_id integer not null default -1,

	last_edit timestamp not null default current_timestamp on update current_timestamp,
	
	# adding unique constraint to users_id
    CONSTRAINT uc_dailylog_users UNIQUE (dailylog_id)

) ENGINE =InnoDB default CHARSET=utf8;
alter table dailylog_users add index (dailylog_id);
alter table dailylog_users add constraint constr_dailylogid UNIQUE (dailylog_id);
alter table dailylog_users add foreign key (dailylog_id) 
   references dailylog (id) on delete cascade
   			 on update cascade;
alter table dailylog_users add index (users_id);
alter table dailylog_users add foreign key (users_id) 
   references users (id) on delete cascade
   			 on update cascade;

