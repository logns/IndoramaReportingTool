
# bu.sql
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
('UT'),
('MS1'),
('MS2'),
('PM1'),
('PM2'),
('PM1-2'),
('SSP'),
('FL1'),
('FL2'),
('FL3'),
('FL2&3'),
('FL1-2-3'),
('TW1'),
('TW2'),
('TW3'),
('TW2&3'),
('TW1-2-3'),
('PY1'),
('PY2'),
('PY3'),
('PY2 PILOT1'),
('PY3 PILOT2'),
('POY Packing'),
('DDX'),
('DTY-Murata'),
('DTY-Scragg'),
('DTY-Packing'),
('Admin');