# roles.sql
# 04/04/2017 ms
#
# Use: Indorama dashboard /app bu
#
# Change history:
#   4/04/2017 pjd initialize the data
#
drop table if exists bu;

create table bu
(
# Surrogate primary key
id integer auto_increment primary key,

# Role/privileges - mapped to Spring Security
bu_name varchar(32) not null default '',

last_edit timestamp not null default current_timestamp on update current_timestamp

) engine=InnoDB DEFAULT CHARSET=utf8;

insert into bu (bu_name)
values
('bu1'),
('bu2'),
('bu3');