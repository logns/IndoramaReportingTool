# create_databases.sql
# 9/13/2017 ms
# Create database(s) and MySQL user(s) for Indorama project
# mysql -uroot -p  < create_databases.sql
#
# Note: Elastic IP address hard coded. Needs update for production.
#

create database if not exists indorama_poly default character set utf8 default collate utf8_general_ci;

# Access from CDL desktops for development: 128.48.204.*
# Requires matching firewall rules
create user 'indorama_polydba'@'localhost' identified by 'indorama_poly-dba999dev';
create user 'indorama_polyw'@'localhost' identified by 'indorama_poly-rw8dev';

grant all on indorama_poly.* to 'indorama_polydba'@'localhost';
grant SELECT, INSERT, DELETE, UPDATE on indorama_poly.* to 'indorama_polydba'@'localhost';

