package com.lognsys.model;

public class UsersTable {

	private static enum STATUS {
		Active, Passive
	}

	private int id;
	private String name;
	private String email;
	private String bu_name;

	private String status;

	public UsersTable() {
		super();
	}

	
	public String getBu_name() {
		return bu_name;
	}


	public void setBu_name(String bu_name) {
		this.bu_name = bu_name;
	}


	public UsersTable(int id, String name, String email, String bu_name, String status) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.bu_name = bu_name;
		this.status = status;
	}

	public UsersTable(int id, String name, String email, boolean enabled) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		setStatus(enabled);
		this.status = getStatus();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setStatus(boolean enabled) {
		if (enabled) {
			this.status = STATUS.Active.name();
		} else {
			this.status = STATUS.Passive.name();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
