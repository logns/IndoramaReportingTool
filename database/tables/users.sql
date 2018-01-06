/**** users table **/
# user.sql
# Use:  User registration.
# 
# Initialization for testing : tbd
#
# Change History: 
# 	3/15/17 - ms ADD: make username, realname index  
#   31/03/17 - pjd UPDATE: set default notification, enabled = 1 

drop table if exists users;
CREATE TABLE users  
(
	# Surrogate Primary Key	
	id integer auto_increment primary key,
    
    # username is an email address
	username varchar(64) not null default '',

	# Optional realname
	realname varchar(64) not null default '',

	# Optional phone
	phone varchar(32) not null default '',
	
    #Required address
    address varchar(255) not null default '',
    
    # optional state 
    state varchar(32) not null default '',
 
    #optional city
    city varchar(32) not null default '',
    
    #optional zip
  	zipcode varchar(8) not null default '',
		  
	#required by spring security enabled =1 , disabled=0 
	enabled tinyint  not null default 1,
		        
	# Optional notification to users 
	notification tinyint not null default 1,
			        
	#Optional birthdate 	
	birthdate datetime default null,

	last_edit timestamp not null default current_timestamp on update current_timestamp,
	
    password VARCHAR(80) NOT NULL,
	UNIQUE(username, phone)

) ENGINE = InnoDB default CHARSET=utf8;

create index users_username_idx on users(username);
create index users_realname_idx on users(realname(16));
create index users_birthdate_idx on users(birthdate);
create index users_city_idx on users(city);
create index users_address_idx on users(address);
