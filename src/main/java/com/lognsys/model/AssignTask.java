package com.lognsys.model;

public class AssignTask {
	private int id;
	private String title = "";
	private String assigned_to = "";
	private String priority = "";
	private String target_date = "";
	private String done_percentage = "";
	private String dailylogDTO;
	public AssignTask() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AssignTask(int id, String title, String assigned_to, String priority, String target_date,
			String done_percentage, String dailylogDTO) {
		super();
		this.id = id;
		this.title = title;
		this.assigned_to = assigned_to;
		this.priority = priority;
		this.target_date = target_date;
		this.done_percentage = done_percentage;
		this.dailylogDTO = dailylogDTO;
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
	public String getDailylogDTO() {
		return dailylogDTO;
	}
	public void setDailylogDTO(String dailylogDTO) {
		this.dailylogDTO = dailylogDTO;
	}
	@Override
	public String toString() {
		return "AssignTask [id=" + id + ", title=" + title + ", assigned_to=" + assigned_to + ", priority=" + priority
				+ ", target_date=" + target_date + ", done_percentage=" + done_percentage + ", dailylogDTO="
				+ dailylogDTO + "]";
	}
	

}
