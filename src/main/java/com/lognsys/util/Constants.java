package com.lognsys.util;

public class Constants {

	/**
	 * enum contains keys of queries defined in sql.properties.
	 * 
	 * Note: All queries should be added in sql.properties and all keys should
	 * be added to USER_QUERIES enum
	 */
	public enum USER_QUERIES {
		select_realname,insert_users, select_users, delete_users, select_users_exists, select_users_id, delete_users_email, update_users, select_users_username, update_users_device
	}

	public enum ROLES_QUERIES {
		insert_users_roles, insert_users_roleid, select_roles_all, select_role_byuserid, update_roles_byuser
	}



	/**
	 * enum contains keys of queries defined in sql.properties.
	 * 
	 * Note: All queries should be added in sql.properties and all keys should
	 * be added to GROUP_QUERIES enum
	 */
	public enum GROUP_QUERIES {

		insert_user_groups, insert_groups, insert_dramas_groups, insert_drama_auditoriums, select_groups_all, insert_subgroups_groups, select_groupname_byuserid, select_id_bygroupname, select_usersbygroups, select_usersgroups_all, select_groupname_bydramaid, select_dramasbygroups, select_dramasgroups_all, select_groups_exists, update_group_byuser, select_subgroup_bygroup, select_count_groups, delete_groups, select_has_subgroup

	}
	public enum DEPARTMENT_QUERIES {

		insert_user_departments, insert_departments,
		select_departments_all, select_departmentname_byuserid,
		select_id_bydepartmentname, select_usersbydepartments,
		select_usersdepartments_all, select_departments_exists,
		select_count_departments, delete_departments,
		update_departments_byuser

	}
	public enum BU_QUERIES {
		insert_user_bu, insert_bu,
		select_bu_all, select_bu_byuserid,
		select_id_bybu, select_usersbybu,
		select_usersbu_all, select_bu_exists,
		select_count_bu, delete_bu,select_bu_by_bu_name,
		update_bu_byuser

	}

	public enum DAILYLOG_QUERIES {
		insert_dailylog,select_dailylog_by_title,
		insert_dailylog_bu,
		insert_dailylog_users,
		select_dailylogs_all,
		select_description_and_id,
		delete_by_assign_task_id
	
	}
	public enum ASSIGN_TASK_QUERIES {
		insert_assign_task,
		select_isexist_assigntask_title,
		select_all_assigntask,
		select_assigntask_by_id,
		select_assigntask_by_title,
		select_assigntask_getcount,
		update_assign_task,
		delete_assign_task_by_id,
		delete_assign_task_by_title
	}
	public enum ASSIGN_TASK_DAILYLOG_QUERIES {
		insert_assign_task_dailylog
	}
	
	/**
	 * enum contains keys of json files and their directory path defined in
	 * application.properties.
	 * 
	 * Note: All the files should be specified in application.properties.
	 */
	public enum JSON_FILES {
		user_filename, dailylogs_filename, adverts_filename, booking_filename, notification_filename, assigntask_filename,realname_filename
	}

	/**
	 * Fields defined here are exactly same as in database. This is to identify
	 * fieldname in sql query and protect from any misnomers of the fieldname
	 * with database
	 * 
	 * Caveat : Any change in the database fields adding or dropping requires to
	 * change field names here
	 * 
	 */
	public enum USER_FIELD_NAMES {
		usersId, realname, username, auth_id, phone, location, provenance, birthdate, enabled, notification, device, address, city, state, zipcode, company_name, title, role, group
	}

	public enum DAILYLOG_TABLE_FIELD_NAMES {
		id, assigntask_id, assign_task_title, description, assigned_to, done_percentage,target_date,status,time
	}

	public enum BU_FIELDNAME {
		buId, bu_name
	}

		// REST URL constants
	public static final String DRAMA_LIST_URL = "/dramalist";

	/**
	 * REST CONTROLLER APPLICATION PROPERTIES STATUS MESSAGES Note: Please make
	 * sure any changes made to Enum REST_MSGS needs corresponding changes to
	 * application.properties. Failure to do will create a bug
	 */
	public enum REST_MSGS {
		response_userempty, response_userinvalid, response_userexists,
		response_dramaexists,response_dramaempty,
		response_auditoriumempty,
		response_ratingsuccess,response_bookingmempty	
	}
	public enum TYPES_ARRAY {
		jobtype,
		recordtype,
		status,
		shift,priority,done_percentage
		}

	/**
	 * Exception Messages
	 */
	public enum EXCEPTIONS_MSG {
		exception_userinvalid, exception_userempty, exception_allgroups, exception_database, exception_groupduplicate,
		
		data_access_exception,data_access_exception_delete,data_access_exception_edit,
		
		something_went_wrong
		
	}

	public enum DEFAULT_GROUP {
		NONE
	}

	public enum DEFAULT_ROLE {
		GUEST
	}

}
