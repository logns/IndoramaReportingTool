package com.lognsys.dao.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "assign_task")
public class AssignTaskDTO {
	@Id
	private int id;
	private String title = "";
	private String assigned_to = "";
	private String priority = "";
	private String target_date = "";
	private String done_percentage = "";
	private String created_by = "";
	private String status = "";
	public AssignTaskDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AssignTaskDTO(int id, String title, String assigned_to, String priority, String target_date,
			String done_percentage,String created_by) {
		super();
		this.id = id;
		this.title = title;
		this.assigned_to = assigned_to;
		this.priority = priority;
		this.target_date = target_date;
		this.done_percentage = done_percentage;
		this.created_by = created_by;
	}
	
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the created_by
	 */
	public String getCreated_by() {
		return created_by;
	}
	/**
	 * @param created_by the created_by to set
	 */
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAssigned_to() {
		return assigned_to;
	}
	public void setAssigned_to(String assigned_to) {
		this.assigned_to = assigned_to;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getTarget_date() {
		return target_date;
	}
	public void setTarget_date(String target_date) {
		this.target_date = target_date;
	}
	public String getDone_percentage() {
		return done_percentage;
	}
	public void setDone_percentage(String done_percentage) {
		this.done_percentage = done_percentage;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AssignTaskDTO [id=" + id + ", title=" + title + ", assigned_to=" + assigned_to + ", priority="
				+ priority + ", target_date=" + target_date + ", done_percentage=" + done_percentage + ", created_by="
				+ created_by + "]";
	}

}
