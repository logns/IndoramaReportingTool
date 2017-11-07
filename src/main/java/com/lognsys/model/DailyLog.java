package com.lognsys.model;

import org.springframework.data.annotation.Id;

/**
 * 
 * Description: User class is Value Object which populates client side object.
 * This takes care of client side validation.
 * 
 * Change LOG :
 * 
 * 1) PJD - 04/05/17 Added Fields String role, String group
 * 
 * @author pdoshi
 */

public class DailyLog {

	private String users = "";
	private String bu = "";
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
	private String realname = "";
	private String dailylog_title = "";
	
	public DailyLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public DailyLog(String users, String bu, int id, String machine, String description, String timefrom, String timeto,
			String spareparts, String attendby, String jobtype, String dates, String recordtype, String status,
			String shift, String realname, String dailylog_title) {
		super();
		this.users = users;
		this.bu = bu;
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
		this.realname = realname;
		this.dailylog_title = dailylog_title;
	}


	public DailyLog(int id, String machine,
			String description, String timefrom, String timeto, String spareparts, String attendby, String jobtype,
			String dates, String recordtype, String status,String shift) {
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
	}

	public String getUsers() {
		return users;
	}


	public void setUsers(String users) {
		this.users = users;
	}


	public String getDailylog_title() {
		return dailylog_title;
	}


	public void setDailylog_title(String dailylog_title) {
		this.dailylog_title = dailylog_title;
	}


	public void setDates(String dates) {
		this.dates = dates;
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

	public void setDate(String dates) {
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
/*
	public String getUsers() {
		return users;
	}

	public void setUsers(String users) {
		this.users = users;
	}*/

	public String getBu() {
		return bu;
	}

	public void setBu(String bu) {
		this.bu = bu;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}


	@Override
	public String toString() {
		return "DailyLog [users=" + users + ", bu=" + bu + ", id=" + id + ", machine=" + machine + ", description="
				+ description + ", timefrom=" + timefrom + ", timeto=" + timeto + ", spareparts=" + spareparts
				+ ", attendby=" + attendby + ", jobtype=" + jobtype + ", dates=" + dates + ", recordtype=" + recordtype
				+ ", status=" + status + ", shift=" + shift + ", realname=" + realname + ", dailylog_title="
				+ dailylog_title + "]";
	}
	
}
