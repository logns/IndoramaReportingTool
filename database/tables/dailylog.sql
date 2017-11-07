

/**** dailylog table **/
# user.sql
# Use:  User registration.
# 
# Initialization for testing : tbd
#
# Change History: 
# 	3/15/17 - ms ADD: make username, realname index  
#   31/03/17 - pjd UPDATE: set default notification, enabled = 1 

drop table if exists dailylog;
CREATE TABLE dailylog  
(
	# Surrogate Primary Key	
	id integer auto_increment primary key,

	# Optional notification to users 
	dailylog_title varchar(150) not null default "",
    
    # date  
	dates varchar(64) not null default '',

	# Optional shift
	shift varchar(64) not null default '',

	# Optional notification to users 
	machine varchar(64) not null default "",
			        
	#Optional birthdate 	
	description VARCHAR(255) default null,
	
    #Optional birthdate 	
	timefrom TIME(6) NOT NULL ,
	
    #Optional birthdate 	
	timeto TIME(6) NOT NULL ,
    
	# Optional notification to users 
	spareparts varchar(64) not null default "",
    
	# Optional notification to users 
	attendby varchar(64) not null default "",

	# Optional notification to users 
	jobtype varchar(64) not null default "",

	# Optional notification to users 
	recordtype varchar(64) not null default "",
	
	# Optional notification to users 
	status varchar(64) not null default "",
	
	last_edit timestamp not null default current_timestamp on update current_timestamp
	

) ENGINE = InnoDB default CHARSET=utf8;

create index dailylog_jobtype on dailylog(jobtype);
create index dailylog_status on dailylog(status);
create index dailylog_attendby on dailylog(attendby);
ALTER TABLE `indorama_poly`.`dailylog` 
ADD COLUMN `assign_to` VARCHAR(105) NOT NULL AFTER `last_edit`;