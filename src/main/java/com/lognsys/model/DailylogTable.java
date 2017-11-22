package com.lognsys.model;

public class DailylogTable {

	private int id;
	private int assign_id;
	private String assign_task_title = "";
	private String description = "";
	private String assigned_to = "";
	private String target_date = "";
	private String done_percentage = "";
	private String status = "";
	private String time = "";
	public DailylogTable(int id, int assign_id, String assign_task_title, String description, String assigned_to,
			String target_date, String done_percentage, String status,String time) {
		super();
		this.id = id;
		this.assign_id = assign_id;
		this.assign_task_title = assign_task_title;
		this.description = description;
		this.assigned_to = assigned_to;
		this.target_date = target_date;
		this.done_percentage = done_percentage;
		this.status = status;
		this.time = time;
	}
	public DailylogTable() {
		super();
	}
	
	@Override
	public String toString() {
		return "DailylogTable [id=" + id + ", assign_id=" + assign_id + ", assign_task_title=" + assign_task_title
				+ ", description=" + description + ", assigned_to=" + assigned_to + ", target_date=" + target_date
				+ ", done_percentage=" + done_percentage + ", status=" + status + ", time=" + time + "]";
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAssign_id() {
		return assign_id;
	}
	public void setAssign_id(int assign_id) {
		this.assign_id = assign_id;
	}
	public String getAssign_task_title() {
		return assign_task_title;
	}
	public void setAssign_task_title(String assign_task_title) {
		this.assign_task_title = assign_task_title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAssigned_to() {
		return assigned_to;
	}
	public void setAssigned_to(String assigned_to) {
		this.assigned_to = assigned_to;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
