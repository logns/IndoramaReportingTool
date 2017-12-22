#!/bin/sh
#
# @author :  pjd - 17/03/17
# This script initialises all the tables in kalrav databases 
# CHANGE LOG : Added environment.sh for installing scripts on dev/stage/prod 
##

#source envronment file
 ../../conf/environment.sh



#users_
mysql kalrav -e "drop table bu;"
mysql kalrav -e "drop table users;"
mysql kalrav -e "drop table users_bu;"

#main tables
mysql kalrav -e "drop table users_roles;"
mysql kalrav -e "drop table roles;"
mysql kalrav -e "drop table assign_task_dailylog;"
mysql kalrav -e "drop table assign_task;"
mysql kalrav -e "drop table dailylog;"
mysql kalrav -e "drop table dailylog_bu;"
mysql kalrav -e "drop table dailylog_users;"
mysql kalrav -e "drop table password_change_requests;"









