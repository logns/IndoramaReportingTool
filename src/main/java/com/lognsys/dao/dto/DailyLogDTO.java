package com.lognsys.dao.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dailylogs")
public class DailyLogDTO {
	@Id
	private int id;

	private int assign_task_id;
	private String assign_task_title = "";
	private String target_date = "";
	private String shift = "";
	private String machine = "";
	private String description = "";
	private String timefrom = "";
	private String timeto = "";
	private String spare_parts = "";
	private String attendby = "";
	private String jobtype = "";
	private String recordtype = "";
	private String status = "";
	private String assigned_to = "";
	private String done_percentage = "";
	private String bu = "";
	private String last_edit = "";

	public String getLast_edit() {
		return last_edit;
	}

	public void setLast_edit(String last_edit) {
		this.last_edit = last_edit;
	}

	public DailyLogDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getAssigned_to() {
		return assigned_to;
	}


	public void setAssigned_to(String assigned_to) {
		this.assigned_to = assigned_to;
	}


	public DailyLogDTO(int id,  String target_date, String shift, String machine,
			String description, String timefrom, String timeto, String spare_parts, String attendby, String jobtype,
			String recordtype, String status,String last_edit ,String done_percentage) {
		super();
		this.id = id;
		this.target_date = target_date;
		this.shift = shift;
		this.machine = machine;
		this.description = description;
		this.timefrom = timefrom;
		this.timeto = timeto;
		this.spare_parts = spare_parts;
		this.attendby = attendby;
		this.jobtype = jobtype;
		this.recordtype = recordtype;
		this.status = status;
		this.last_edit = last_edit;
		this.done_percentage = done_percentage;
	}
	

	public DailyLogDTO(int id, String last_edit, String shift, String machine, String description, String timefrom,
			String timeto, String spare_parts, String attendby, String jobtype, String recordtype, String status,
			String done_percentage) {

		this.id = id;
		this.last_edit = last_edit;
		this.shift = shift;
		this.machine = machine;
		this.description = description;
		this.timefrom = timefrom;
		this.timeto = timeto;
		this.spare_parts = spare_parts;
		this.attendby = attendby;
		this.jobtype = jobtype;
		this.recordtype = recordtype;
		this.status = status;
		this.done_percentage = done_percentage;
	}

	public int getAssign_task_id() {
		return assign_task_id;
	}

	public void setAssign_task_id(int assign_task_id) {
		this.assign_task_id = assign_task_id;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DailyLogDTO [id=" + id +  ", assign_task_id=" + assign_task_id
				+ ", assign_task_title=" + assign_task_title + ", target_date=" + target_date + ", shift=" + shift
				+ ", machine=" + machine + ", description=" + description + ", timefrom=" + timefrom + ", timeto="
				+ timeto + ", spare_parts=" + spare_parts + ", attendby=" + attendby + ", jobtype=" + jobtype
				+ ", recordtype=" + recordtype + ", status=" + status + ", assigned_to=" + assigned_to
				+ ", done_percentage=" + done_percentage + ", bu=" + bu + ", last_edit=" + last_edit + "]";
	}

	public String getBu() {
		return bu;
	}

	public void setBu(String bu) {
		this.bu = bu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAssign_task_title() {
		return assign_task_title;
	}

	public void setAssign_task_title(String assign_task_title) {
		this.assign_task_title = assign_task_title;
	}

	public String getTarget_date() {
		return target_date;
	}

	public void setTarget_date(String target_date) {
		this.target_date = target_date;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTimefrom() {
		return timefrom;
	}

	public void setTimefrom(String timefrom) {
		this.timefrom = timefrom;
	}

	public String getTimeto() {
		return timeto;
	}

	public void setTimeto(String timeto) {
		this.timeto = timeto;
	}

	public String getSpare_parts() {
		return spare_parts;
	}

	public void setSpare_parts(String spare_parts) {
		this.spare_parts = spare_parts;
	}

	public String getAttendby() {
		return attendby;
	}

	public void setAttendby(String attendby) {
		this.attendby = attendby;
	}

	public String getJobtype() {
		return jobtype;
	}

	public void setJobtype(String jobtype) {
		this.jobtype = jobtype;
	}

	public String getRecordtype() {
		return recordtype;
	}

	public void setRecordtype(String recordtype) {
		this.recordtype = recordtype;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDone_percentage() {
		return done_percentage;
	}

	public void setDone_percentage(String done_percentage) {
		this.done_percentage = done_percentage;
	}
	
}