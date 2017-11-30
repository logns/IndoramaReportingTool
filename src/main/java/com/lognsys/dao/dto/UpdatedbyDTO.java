package com.lognsys.dao.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "updatedby")
public class UpdatedbyDTO {

	@Id
	private int id;
	private String updaterby_realname = "";
	private String updated_time = "";
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUpdaterby_realname() {
		return updaterby_realname;
	}
	public void setUpdaterby_realname(String updaterby_realname) {
		this.updaterby_realname = updaterby_realname;
	}
	public String getUpdated_time() {
		return updated_time;
	}
	public void setUpdated_time(String updated_time) {
		this.updated_time = updated_time;
	}
	@Override
	public String toString() {
		return "UpdatedbyDTO [id=" + id + ", updaterby_realname=" + updaterby_realname + ", updated_time="
				+ updated_time + "]";
	}
	public UpdatedbyDTO(int id, String updaterby_realname, String updated_time) {
		super();
		this.id = id;
		this.updaterby_realname = updaterby_realname;
		this.updated_time = updated_time;
	}
	public UpdatedbyDTO() {
		super();
	}
	
}
