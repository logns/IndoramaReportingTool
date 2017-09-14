package com.lognsys.dao.dto;

public class RolesDTO {

	private int id;
	private String role;
	
	public RolesDTO() {
		// no-arg constructor
	}
	
	
	public RolesDTO(int id, String role) {
		super();
		this.id = id;
		this.role = role;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}
	

}
