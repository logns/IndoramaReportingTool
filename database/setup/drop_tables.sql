#!/bin/sh
#
# @author :  pjd - 17/03/17
# This script initialises all the tables in kalrav databases 
# CHANGE LOG : Added environment.sh for installing scripts on dev/stage/prod 
##

#source envronment file
 ../../conf/environment.sh



#users_
mysql kalrav -e "drop table users_bu;"
mysql kalrav -e "drop table users_roles;"
mysql kalrav -e "drop table users_departments;"

#main tables
mysql kalrav -e "drop table users;"
mysql kalrav -e "drop table bu;"
mysql kalrav -e "drop table departments;"
mysql kalrav -e "drop table roles;"









