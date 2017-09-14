# roles.sql
# 04/04/2017 ms
#
# Use: Indorama dashboard /app roles
#
# Change history:
#   4/04/2017 pjd initialize the data
#

drop table if exists roles;

create table roles
(
# Surrogate primary key
id integer auto_increment primary key,

# Role/privileges - mapped to Spring Security
role varchar(32) not null default '',

last_edit timestamp not null default current_timestamp on update current_timestamp

) engine=InnoDB DEFAULT CHARSET=utf8;

insert into roles (role)
values
('ADMIN'),
('GROUP_USER');