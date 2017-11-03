package com.lognsys.dao.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dailylogs")
public class DailyLogDTO {
	@Id
	private int id;
	private String machine = "";
	private String description = "";
	private String timefrom = "";
	private String timeto = "";
	private String spareparts = "";
	private String attendby = "";
	private String jobtype = "";
	private String dates = "";
	private String recordtype = "";
	private String status = "";
	private String shift = "";
	private String bu_name = "";
	
	public DailyLogDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DailyLogDTO(int id, String machine,
			String description, String timefrom, String timeto, String spareparts, String attendby, String jobtype,
			String dates, String recordtype, String status,String shift,String bu_name) {
		super();
		this.id = id;
		this.machine = machine;
		this.description = description;
		this.timefrom = timefrom;
		this.timeto = timeto;
		this.spareparts = spareparts;
		this.attendby = attendby;
		this.jobtype = jobtype;
		this.dates = dates;
		this.recordtype = recordtype;
		this.status = status;
		this.shift = shift;
		this.bu_name = bu_name;
	}

	public String getbu_name() {
		return bu_name;
	}

	public void setbu_name(String bu_name) {
		this.bu_name = bu_name;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
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

	public String getJobtype() {
		return jobtype;
	}

	public void setJobtype(String jobtype) {
		this.jobtype = jobtype;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getSpareparts() {
		return spareparts;
	}
	public void setSpareparts(String spareparts) {
		this.spareparts = spareparts;
	}
	public String getAttendby() {
		return attendby;
	}
	public void setAttendby(String attendby) {
		this.attendby = attendby;
	}

	@Override
	public String toString() {
		return "DailyLogDTO [id=" + id  
				+ ", machine=" + machine + ", description=" + description + ", timefrom=" + timefrom + ", timeto="
				+ timeto + ", spareparts=" + spareparts + ", attendby=" + attendby + ", jobtype=" + jobtype + ", dates="
				+ dates  + ", recordtype=" + recordtype + ", status=" + status
				+ ", shift=" + shift + ", bu_name=" + bu_name + "]";
	}

	
	
}
