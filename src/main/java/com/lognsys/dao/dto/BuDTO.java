package com.lognsys.dao.dto;

public class BuDTO {

	private int id;
	private String bu_name;
	
	public BuDTO() {
		// no-arg constructor
	}
	
	
	public BuDTO(int id, String bu_name) {
		super();
		this.id = id;
		this.bu_name = bu_name;
	}


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



}
