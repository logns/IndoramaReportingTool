package com.lognsys.util;

public class Constants {

	/**
	 * enum contains keys of queries defined in sql.properties.
	 * 
	 * Note: All queries should be added in sql.properties and all keys should
	 * be added to USER_QUERIES enum
	 */
	public enum USER_QUERIES {
		insert_users, select_users, delete_users, select_users_exists, select_users_id, delete_users_email, update_users, select_users_username, update_users_device
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
		select_count_bu, delete_bu,
		update_bu_byuser

	}

	public enum DAILYLOG_QUERIES {
		insert_dailylog,
		insert_dailylog_bu,
		insert_dailylog_users,
		select_dailylogs_all
	}

	/**
	 * enum contains keys of json files and their directory path defined in
	 * application.properties.
	 * 
	 * Note: All the files should be specified in application.properties.
	 */
	public enum JSON_FILES {
		user_filename, dailylogs_filename, adverts_filename, booking_filename, notification_filename
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

	public enum DRAMA_FIELD_NAMES {
		dramasId, title, imageurl, drama_length, date, genre, star_cast, description, director, writer, music, avg_rating, group_name, auditorium_name, group_id, auditorium_id, dramas_language
	}

	public enum BU_FIELDNAME {
		buId, bu_name
	}

	public enum AUDITORIUMS_FIELDNAME {
		auditoriumsId, auditorium_name, address, latitude, longitude
	}

	public enum BOOKING_FIELD_NAMES {
		id, booking_date, confirmation_no, users_id, dramas_id, auditoriums_id, price, status
	}

	public enum BOOKEDSEATS_FIELD_NAMES {
		id, booking_id, row_seats_id, seat_status
	}

	public enum DEVICE_FIELD_NAMES {
		id, users_id, deviceToken
	}

	public enum ROWSEAT_FIELD_NAMES {
		id, row_num, row_name, seat_num, auditoriums_id
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
		jobtype
		}

	/**
	 * Exception Messages
	 */
	public enum EXCEPTIONS_MSG {
		exception_userinvalid, exception_userempty, exception_allgroups, exception_database, exception_groupduplicate
	}

	public enum DEFAULT_GROUP {
		NONE
	}

	public enum DEFAULT_ROLE {
		GUEST
	}

}
