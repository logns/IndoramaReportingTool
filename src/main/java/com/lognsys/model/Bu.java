package com.lognsys.model;

/**
 * Group POJO as value object for retrieving values from form
 * 
 * @author pdoshi
 *
 */
public class Bu {
	private int id;
	private String bu_name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBu_name() {
		return bu_name;
	}
	public void setBu_name(String bu_name) {
		this.bu_name = bu_name;
	}
	public Bu(int id, String bu_name) {
		super();
		this.id = id;
		this.bu_name = bu_name;
	}
	public Bu() {
		super();
	}
	@Override
	public String toString() {
		return "Bu [id=" + id + ", bu_name=" + bu_name + "]";
	}
	
	
}
