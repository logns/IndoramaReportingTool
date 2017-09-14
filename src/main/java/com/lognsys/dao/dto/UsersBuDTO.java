package com.lognsys.dao.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UsersBuDTO {

	@Id
	private int id;

	@DBRef
	private BuDTO buDTO;

	@DBRef
	private UsersDTO user;

	public UsersBuDTO(int id, BuDTO buDTO, UsersDTO user) {
		super();
		this.id = id;
		this.buDTO = buDTO;
		this.user = user;
	}

	public UsersBuDTO() {

	}

	
	public BuDTO getBuDTO() {
		return buDTO;
	}

	public void setBuDTO(BuDTO buDTO) {
		this.buDTO = buDTO;
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

}
