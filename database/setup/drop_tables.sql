#!/bin/sh
#
# @author :  pjd - 17/03/17
# This script initialises all the tables in indorama_poly databases 
# CHANGE LOG : Added environment.sh for installing scripts on dev/stage/prod 
##




#users_
mysql indorama_poly -e "drop table bu;"
mysql indorama_poly -e "drop table users;"
mysql indorama_poly -e "drop table users_bu;"


#main tables
mysql indorama_poly -e "drop table users_roles;"
mysql indorama_poly -e "drop table roles;"
mysql indorama_poly -e "drop table assign_task_dailylog;"
mysql indorama_poly -e "drop table assign_task;"
mysql indorama_poly -e "drop table dailylog;"
mysql indorama_poly -e "drop table dailylog_bu;"
mysql indorama_poly -e "drop table dailylog_users;"
mysql indorama_poly -e "drop table password_change_requests;"









