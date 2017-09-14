#!/bin/sh
#
# @author :  ms - 13/09/17
# This script initialises all the tables in kalrav databases 
# CHANGE LOG : Added environment.sh for installing scripts on dev/stage/prod 
#  
# NOTE : Order of the sql tables is important. 
# 
##

#source envronment file
. ../../conf/environment.sh
#users, groups
mysql kalrav < ${PROJECT_DIR}/database/tables/users_bu.sql 
mysql kalrav < ${PROJECT_DIR}/database/tables/users_roles.sql
mysql kalrav < ${PROJECT_DIR}/database/tables/bu.sql 
mysql kalrav < ${PROJECT_DIR}/database/tables/departments.sql 

mysql kalrav < ${PROJECT_DIR}/database/tables/users_departments.sql 
mysql kalrav < ${PROJECT_DIR}/database/tables/users.sql
#roles
mysql kalrav < ${PROJECT_DIR}/database/tables/roles.sql 


