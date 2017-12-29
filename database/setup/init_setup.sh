#!/bin/sh
#
# @author :  ms - 13/09/17
# This script initialises all the tables in indorama_poly databases 
# CHANGE LOG : Added environment.sh for installing scripts on dev/stage/prod 
#
#  PJD - 20/12/2017 - Adding 
#  
# NOTE : Order of the sql tables is important. 
# 
##

#get absolute path of the init_setup script file
scriptDir="$(cd "$(dirname "$0")" && pwd -P)"

# Get dirpath of environment.sh file
envDir=`echo $scriptDir | sed -e 's/\/database.*//'`

source $envDir/conf/environment.sh

#users, groups
mysql indorama_poly < ${PROJECT_DIR}/database/tables/bu.sql 
mysql indorama_poly < ${PROJECT_DIR}/database/tables/users.sql
mysql indorama_poly < ${PROJECT_DIR}/database/tables/roles.sql 
mysql indorama_poly < ${PROJECT_DIR}/database/tables/users_bu.sql 
mysql indorama_poly < ${PROJECT_DIR}/database/tables/users_roles.sql
mysql indorama_poly < ${PROJECT_DIR}/database/tables/password_change_requests.sql 



#roles
mysql indorama_poly < ${PROJECT_DIR}/database/tables/assign_task_dailylog.sql 
mysql indorama_poly < ${PROJECT_DIR}/database/tables/assign_task.sql 
mysql indorama_poly < ${PROJECT_DIR}/database/tables/dailylog.sql 
mysql indorama_poly < ${PROJECT_DIR}/database/tables/dailylog_bu.sql
mysql indorama_poly < ${PROJECT_DIR}/database/tables/dailylog_users.sql 
mysql indorama_poly < ${PROJECT_DIR}/database/tables/persistence_logins.sql
