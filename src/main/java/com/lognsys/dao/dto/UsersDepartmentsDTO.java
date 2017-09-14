package com.lognsys.dao.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UsersDepartmentsDTO {

	@Id
	private int id;

	@DBRef
	private DepartmentsDTO departmentsDTO;

	@DBRef
	private UsersDTO user;

	public UsersDepartmentsDTO(int id, DepartmentsDTO departmentsDTO, UsersDTO user) {
		super();
		this.id = id;
		this.departmentsDTO = departmentsDTO;
		this.user = user;
	}

	public UsersDepartmentsDTO() {

	}

	
	public UsersDTO getUser() {
		return user;
	}

	public void setUser(UsersDTO user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DepartmentsDTO getDepartmentsDTO() {
		return departmentsDTO;
	}

	public void setDepartmentsDTO(DepartmentsDTO departmentsDTO) {
		this.departmentsDTO = departmentsDTO;
	}

}
