package com.lognsys.model;

/**
 * Group POJO as value object for retrieving values from form
 * 
 * @author pdoshi
 *
 */
public class Departments {
	private int id;
	private String department_name;
	public Departments(int id, String department_name) {
		super();
		this.id = id;
		this.department_name = department_name;
	}
	public Departments() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	@Override
	public String toString() {
		return "Departments [id=" + id + ", department_name=" + department_name + "]";
	}


}
