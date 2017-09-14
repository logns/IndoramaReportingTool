package com.lognsys.dao.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class UserRolesDTO {
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RolesDTO getRoles() {
		return roles;
	}

	public void setRoles(RolesDTO roles) {
		this.roles = roles;
	}

	public UsersDTO getUser() {
		return user;
	}

	public void setUser(UsersDTO user) {
		this.user = user;
	}

	@Id
	private int id;

	@DBRef
	private RolesDTO roles;

	@DBRef
	private UsersDTO user;

}
